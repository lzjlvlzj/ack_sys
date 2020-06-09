package org.ack.sys;

import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreatorTest {
    @Test
    public void testMbg() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String path = CreatorTest.class.getClassLoader().getResource("config/generatorConfig.xml").getPath();
        //path = "resources/config/generatorConfig.xml";
        File configFile = new File(path);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
