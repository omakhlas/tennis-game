import groovy.ant.AntBuilder;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.*;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.config.CodegenConfigurator;
import org.gradle.work.*;

import java.io.File;
import java.util.Map;

public abstract class GenerateOpenApiServerTask extends DefaultTask {

    @Input
    public abstract Property<String> getBasePackage();

    @InputFile
    @Incremental
    public abstract RegularFileProperty getSpecFile();

    @OutputDirectory
    public abstract DirectoryProperty getOutputDir();

    @TaskAction
    public void generate(InputChanges inputChanges) {
        // Delete any previously generated code
        File generatedKotlinDirectory = getOutputDir().get().dir("src/main/java").getAsFile();
        var ant = new AntBuilder();
        ant.invokeMethod("delete", Map.of("dir", generatedKotlinDirectory.getAbsolutePath()));

        // Configure OpenAPI Generator
        var configurator = new CodegenConfigurator()
                .setGeneratorName("spring")
                .setInputSpec(getSpecFile().get().getAsFile().getAbsolutePath())
                .setOutputDir(getOutputDir().get().toString())
                .setApiPackage(getBasePackage().get())
                .setModelPackage(getBasePackage().get() + ".model")
                .setModelNameSuffix("ApiDto")
                .addAdditionalProperty("annotationLibrary", "none")
                .addAdditionalProperty("documentationProvider", "none")
                .addAdditionalProperty("serializationLibrary", "jackson")
                .addAdditionalProperty("hideGenerationTimestamp", "true")
                .addAdditionalProperty("interfaceOnly", "true")
                .addAdditionalProperty("useSpringBoot3", "true")
                .addAdditionalProperty("useTags", "true")
                .addAdditionalProperty("skipApiUtil", true)
                .addAdditionalProperty("openApiNullable", false)
                .setValidateSpec(false);

        try {
            new DefaultGenerator().opts(configurator.toClientOptInput()).generate();
        } catch (RuntimeException rte) {
            throw new GradleException("Code generation failed", rte);
        }
    }
}