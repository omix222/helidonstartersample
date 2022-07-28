//
// このファイルは、Eclipse Implementation of JAXB、v3.0.0によって生成されました 
// https://eclipse-ee4j.github.io/jaxb-riを参照してください 
// ソース・スキーマの再コンパイル時にこのファイルの変更は失われます。 
// 生成日: 2022.07.28 時間 11:01:20 PM JST 
//


package io.helidon.archetypes.tests.jaxb;

import jakarta.annotation.Generated;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>persistence-unit-transaction-typeのJavaクラス。
 * 
 * <p>次のスキーマ・フラグメントは、このクラス内に含まれる予期されるコンテンツを指定します。
 * <pre>
 * &lt;simpleType name="persistence-unit-transaction-type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="JTA"/&gt;
 *     &lt;enumeration value="RESOURCE_LOCAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "persistence-unit-transaction-type")
@XmlEnum
@Generated(value = "com.sun.tools.xjc.Driver", comments = "JAXB RI v3.0.0", date = "2022-07-28T11:01:20+09:00")
public enum PersistenceUnitTransactionType {

    JTA,
    RESOURCE_LOCAL;

    public String value() {
        return name();
    }

    public static PersistenceUnitTransactionType fromValue(String v) {
        return valueOf(v);
    }

}
