package com.csy.mybatis.derby;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;

public class ExampleObjectFactory extends DefaultObjectFactory {
  private Properties properties;
  @Override
  public <T> T create(Class<T> type) {
    return super.<T>create(type);
  }

  @Override
  public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
    return super.<T>create(type, constructorArgTypes, constructorArgs);
  }

  @Override
  public void setProperties(Properties properties) {
    super.setProperties(properties);
    this.properties = properties;
  }

  public Properties getProperties() {
    return properties;
  }

}
