/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.middleware.distributed.transaction.config;

import com.acme.middleware.distributed.transaction.jdbc.SwitchableMySQLReplicationDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * 可切换 MySQL 复制 {@link DataSource} 配置类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see SwitchableMySQLReplicationDataSource
 * @since 1.0.0
 */
@Profile("mysql-replication")
@Configuration(proxyBeanMethods = false)
public class SwitchableMySQLReplicationDataSourceConfiguration implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            return new SwitchableMySQLReplicationDataSource((DataSource) bean);
        }
        return bean;
    }
}
