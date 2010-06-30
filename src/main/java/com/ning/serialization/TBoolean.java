/*
 * Copyright 2010 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.ning.serialization;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;

public class TBoolean implements TBase, java.io.Serializable
{
    public boolean value;

    public final Isset __isset = new Isset();

    public static final class Isset
    {
        public boolean value = false;
    }

    public TBoolean()
    {
    }

    public TBoolean(
        boolean value)
    {
        this();
        this.value = value;
        this.__isset.value = true;
    }

    public void read(TProtocol iprot) throws TException
    {
        TField field;
        iprot.readStructBegin();
        while (true) {
            field = iprot.readFieldBegin();
            if (field.type == TType.STOP) {
                break;
            }
            switch (field.id) {
                case 1:
                    if (field.type == TType.BOOL) {
                        this.value = iprot.readBool();
                        this.__isset.value = true;
                    }
                    else {
                        TProtocolUtil.skip(iprot, field.type);
                    }
                    break;
                default:
                    TProtocolUtil.skip(iprot, field.type);
                    break;
            }
            iprot.readFieldEnd();
        }
        iprot.readStructEnd();
    }

    public void write(TProtocol oprot) throws TException
    {
        TStruct struct = new TStruct("TBoolean");
        oprot.writeStructBegin(struct);
        TField field = new TField("value", TType.BOOL, (short) 1);
        oprot.writeFieldBegin(field);
        oprot.writeBool(this.value);
        oprot.writeFieldEnd();
        oprot.writeFieldStop();
        oprot.writeStructEnd();
    }

    /**
     * @deprecated
     */
    @Override
    public boolean isSet(int i)
    {
        throw new NoSuchMethodError("Not implemented");
    }

    @Override
    public boolean isSet(TFieldIdEnum tFieldIdEnum)
    {
        throw new NoSuchMethodError("Not implemented");
    }

    /**
     * @deprecated
     */
    @Override
    public Object getFieldValue(int i)
    {
        throw new NoSuchMethodError("Not implemented");
    }

    @Override
    public Object getFieldValue(TFieldIdEnum tFieldIdEnum)
    {
        throw new NoSuchMethodError("Not implemented");
    }

    /**
     * @deprecated
     */
    @Override
    public void setFieldValue(int i, Object o)
    {
        throw new NoSuchMethodError("Not implemented");
    }

    @Override
    public void setFieldValue(TFieldIdEnum tFieldIdEnum, Object o)
    {
        throw new NoSuchMethodError("Not implemented");
    }

    @Override
    public TBase deepCopy()
    {
        throw new NoSuchMethodError("Not implemented");
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("TBoolean(");
        sb.append("value:");
        sb.append(this.value);
        sb.append(")");
        return sb.toString();
    }

}

