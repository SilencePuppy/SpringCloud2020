package com.hc;

import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @author 李晓冰
 * @date 2020年11月25日
 */
public class My3Impl extends IntrospectedTableMyBatis3Impl {
    @Override
    protected void calculateJavaClientAttributes() {
        if (this.context.getJavaClientGeneratorConfiguration() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.calculateJavaClientImplementationPackage());
            sb.append('.');
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("DAOImpl");
            this.setDAOImplementationType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("DAO");
            this.setDAOInterfaceType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            if (StringUtility.stringHasValue(this.tableConfiguration.getMapperName())) {
                sb.append(this.tableConfiguration.getMapperName());
            } else {
                if (StringUtility.stringHasValue(this.fullyQualifiedTable.getDomainObjectSubPackage())) {
                    sb.append(this.fullyQualifiedTable.getDomainObjectSubPackage());
                    sb.append('.');
                }

                sb.append(this.fullyQualifiedTable.getDomainObjectName());
                sb.append("Dao");
            }

            this.setMyBatis3JavaMapperType(sb.toString());
            sb.setLength(0);
            sb.append(this.calculateJavaClientInterfacePackage());
            sb.append('.');
            if (StringUtility.stringHasValue(this.tableConfiguration.getSqlProviderName())) {
                sb.append(this.tableConfiguration.getSqlProviderName());
            } else {
                if (StringUtility.stringHasValue(this.fullyQualifiedTable.getDomainObjectSubPackage())) {
                    sb.append(this.fullyQualifiedTable.getDomainObjectSubPackage());
                    sb.append('.');
                }

                sb.append(this.fullyQualifiedTable.getDomainObjectName());
                sb.append("SqlProvider");
            }

            this.setMyBatis3SqlProviderType(sb.toString());
        }
    }


    @Override
    protected String calculateMyBatis3XmlMapperFileName() {
        StringBuilder sb = new StringBuilder();
        if (StringUtility.stringHasValue(this.tableConfiguration.getMapperName())) {
            String mapperName = this.tableConfiguration.getMapperName();
            int ind = mapperName.lastIndexOf(46);
            if (ind == -1) {
                sb.append(mapperName);
            } else {
                sb.append(mapperName.substring(ind + 1));
            }

            sb.append(".xml");
        } else {
            sb.append(this.fullyQualifiedTable.getDomainObjectName());
            sb.append("Mapper.xml");
        }

        return sb.toString();
    }
}
