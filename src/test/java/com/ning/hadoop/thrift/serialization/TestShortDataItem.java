package com.ning.hadoop.thrift.serialization;

import com.ning.serialization.BooleanDataItem;
import com.ning.serialization.DataItem;
import com.ning.serialization.ShortDataItem;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;

public class TestShortDataItem
{
    private final DataItem value0 = new ShortDataItem((short) 0);
    private final DataItem value1 = new ShortDataItem((short) 1);
    private final DataItem value1000 = new ShortDataItem((short) 1000);
    private final DataItem valueMinus1000 = new ShortDataItem((short) -1000);

    @Test(groups = "fast")
    public void testNoArgConstructor() throws Exception
    {
        DataItem item = new ShortDataItem();
        Assert.assertEquals(item.getShort(), Short.valueOf((short) 0));
    }

    @Test(groups = "fast")
    public void testConstructor() throws Exception
    {
        DataItem item1 = new ShortDataItem((short) 100);
        Assert.assertEquals(item1.getShort(), Short.valueOf((short) 100));
    }

    @Test(groups = "fast")
    public void testConvertToBoolean() throws Exception
    {
        Assert.assertEquals(value0.getBoolean().booleanValue(), false);
        Assert.assertEquals(value1.getBoolean().booleanValue(), true);
        Assert.assertEquals(value1000.getBoolean().booleanValue(), true);
        Assert.assertEquals(valueMinus1000.getBoolean().booleanValue(), true);
    }

    @Test(groups = "fast")
    public void testConvertToByte() throws Exception
    {
        Assert.assertEquals(value0.getByte(), Byte.valueOf((byte) 0));
        Assert.assertEquals(value1.getByte(), Byte.valueOf((byte) 1));
        Assert.assertEquals(value1000.getByte(), Byte.valueOf((byte) -24)); //overflow
        Assert.assertEquals(valueMinus1000.getByte(), Byte.valueOf((byte) 24)); //overflow
    }

    @Test(groups = "fast")
    public void testConvertToShort() throws Exception
    {
        Assert.assertEquals(value0.getShort(), Short.valueOf((short) 0));
        Assert.assertEquals(value1.getShort(), Short.valueOf((short) 1));
        Assert.assertEquals(value1000.getShort(), Short.valueOf((short) 1000));
        Assert.assertEquals(valueMinus1000.getShort(), Short.valueOf((short) -1000));
    }

    @Test(groups = "fast")
    public void testConvertToInteger() throws Exception
    {
        Assert.assertEquals(value0.getInteger(), Integer.valueOf(0));
        Assert.assertEquals(value1.getInteger(), Integer.valueOf(1));
        Assert.assertEquals(value1000.getInteger(), Integer.valueOf(1000));
        Assert.assertEquals(valueMinus1000.getInteger(), Integer.valueOf(-1000));
    }

    @Test(groups = "fast")
    public void testConvertToLong() throws Exception
    {
        Assert.assertEquals(value0.getLong(), Long.valueOf(0));
        Assert.assertEquals(value1.getLong(), Long.valueOf(1));
        Assert.assertEquals(value1000.getLong(), Long.valueOf(1000));
        Assert.assertEquals(valueMinus1000.getLong(), Long.valueOf(-1000));
    }

    @Test(groups = "fast")
    public void testConvertToDouble() throws Exception
    {
        Assert.assertEquals(value0.getDouble(), 0.0);
        Assert.assertEquals(value1.getDouble(), 1.0);
        Assert.assertEquals(value1000.getDouble(), 1000.0);
        Assert.assertEquals(valueMinus1000.getDouble(), -1000.0);
    }

    @Test(groups = "fast")
    public void testConvertToStringOk() throws Exception
    {
        Assert.assertEquals(value0.getString(), "0");
        Assert.assertEquals(value1.getString(), "1");
        Assert.assertEquals(value1000.getString(), "1000");
        Assert.assertEquals(valueMinus1000.getString(), "-1000");
    }

    @Test(groups = "fast")
    public void testCompareToAndEquals() throws Exception
    {
        Assert.assertTrue(value0.compareTo(value1) < 0);
        Assert.assertTrue(value1.compareTo(value1) == 0);
        Assert.assertTrue(value1000.compareTo(valueMinus1000) > 0);
        Assert.assertEquals(value1000, new ShortDataItem((short) 1000));
        Assert.assertEquals(value1000.hashCode(), new ShortDataItem((short) 1000).hashCode());
    }

    @Test(groups = "fast")
    public void testToString() throws Exception
    {
        Assert.assertEquals(value0.toString(), "0");
        Assert.assertEquals(value1.toString(), "1");
        Assert.assertEquals(value1000.toString(), "1000");
        Assert.assertEquals(valueMinus1000.toString(), "-1000");
    }

    @Test(groups = "fast")
    public void testReadAndWrite() throws Exception
    {
        ShortDataItem item = new ShortDataItem((short) 1000);
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        DataOutput out = new DataOutputStream(byteOut);
        item.write(out);
        DataInput in = new DataInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
        DataItem inItem = new BooleanDataItem();
        int type = in.readByte();
        Assert.assertEquals(type, DataItem.SHORT_TYPE);
        inItem.readFields(in);
        Assert.assertEquals(item, inItem);
    }
}