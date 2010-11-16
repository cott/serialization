/*
 * Copyright 2010 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.metrics.serialization.event;

import com.ning.metrics.serialization.thrift.ThriftEnvelope;
import com.ning.metrics.serialization.thrift.ThriftField;
import com.ning.metrics.serialization.thrift.ThriftFieldListDeserializer;
import org.apache.thrift.TException;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ThriftToThriftEnvelopeEvent
{
    /**
     * Given a generic thrift object (class generated by the thrift compiler), create a ThriftEnvelopeEvent.
     * The event timestamp defaults to now().
     *
     * @param eventName    Thrift schema name
     * @param thriftObject Thrift instance
     * @param <T>          any Thrift class generated by the thrift compiler
     * @return ThriftEnvelopeEvent which wraps all thrift fields as ThriftFields
     */
    public static <T extends Serializable> ThriftEnvelopeEvent extractEvent(String eventName, T thriftObject)
    {
        return extractEvent(eventName, new DateTime(), thriftObject);
    }

    /**
     * Given a generic thrift object (class generated by the thrift compiler), create a ThriftEnvelopeEvent.
     *
     * @param eventName     Thrift schema name
     * @param eventDateTime the event timestamp
     * @param thriftObject  Thrift instance
     * @param <T>           any Thrift class generated by the thrift compiler
     * @return ThriftEnvelopeEvent which wraps all thrift fields as ThriftFields
     */
    public static <T extends Serializable> ThriftEnvelopeEvent extractEvent(String eventName, DateTime eventDateTime, T thriftObject)
    {
        List<ThriftField> thriftFieldList = new ArrayList<ThriftField>();

        Field[] fields = thriftObject.getClass().getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                ThriftField field = ThriftField.createThriftField(fields[i].getType(), fields[i].get(thriftObject), (short) i);

                // null for the Thrift metaData map and potential other non-supported attributes added by the caller in the thriftObject
                if (field != null) {
                    thriftFieldList.add(field);
                }
            }
            catch (IllegalAccessException ignored) {
            }
        }

        ThriftEnvelope envelope = new ThriftEnvelope(eventName, thriftFieldList);
        return new ThriftEnvelopeEvent(eventDateTime, envelope);
    }

    /**
     * Given a serialized Thrift, generate a ThrifTEnvelopeEvent
     *
     * @param type    Thrift schema name
     * @param payload serialized Thrift
     * @return ThriftEnvelopeEvent representing the Thrift (the event timestamp defaults to now())
     * @throws TException if the payload is not a valid Thrift
     */
    public static ThriftEnvelopeEvent extractEvent(String type, byte[] payload) throws TException
    {
        return extractEvent(type, new DateTime(), payload);
    }

    /**
     * Given a serialized Thrift, generate a ThrifTEnvelopeEvent
     *
     * @param type          Thrift schema name
     * @param eventDateTime the event timestamp
     * @param payload       serialized Thrift
     * @return ThriftEnvelopeEvent representing the Thrift
     * @throws TException if the payload is not a valid Thrift
     */
    public static ThriftEnvelopeEvent extractEvent(String type, DateTime eventDateTime, byte[] payload) throws TException
    {
        final List<ThriftField> list = new ThriftFieldListDeserializer().readPayload(payload);
        final ThriftEnvelope envelope = new ThriftEnvelope(type, list);

        return new ThriftEnvelopeEvent(eventDateTime, envelope);
    }
}
