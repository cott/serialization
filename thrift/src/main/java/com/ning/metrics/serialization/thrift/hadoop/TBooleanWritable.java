/*
 * Copyright 2010-2011 Ning, Inc.
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

package com.ning.metrics.serialization.thrift.hadoop;

public class TBooleanWritable extends ThriftWritable<TBoolean>
{
    public TBooleanWritable()
    {
        super(new TBoolean());
    }

    public TBooleanWritable(boolean value)
    {
        super(new TBoolean(value));
    }

    public int compareTo(Object o)
    {
        Boolean t = get().value;
        Boolean that = ((TBooleanWritable) o).get().value;
        return t.compareTo(that);
    }

    public int hashCode()
    {
        Boolean t = get().value;
        return t.hashCode();
    }

    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TBooleanWritable)) {
            return false;
        }

        Boolean me = get().value;
        Boolean you = ((TBooleanWritable) o).get().value;
        return me.equals(you);
    }
}
