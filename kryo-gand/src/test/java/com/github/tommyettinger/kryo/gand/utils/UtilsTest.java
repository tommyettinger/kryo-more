package com.github.tommyettinger.kryo.gand.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.gand.utils.Choo32Random;
import com.github.tommyettinger.gand.utils.FlowRandom;
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {
    @Test
    public void testChoo32Random() {
        Kryo kryo = new Kryo();
        kryo.register(Choo32Random.class, new Choo32RandomSerializer());

        Choo32Random data = new Choo32Random(-12345L);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Choo32Random data2 = kryo.readObject(input, Choo32Random.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFlowRandom() {
        Kryo kryo = new Kryo();
        kryo.register(FlowRandom.class, new FlowRandomSerializer());

        FlowRandom data = new FlowRandom(-12345L);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FlowRandom data2 = kryo.readObject(input, FlowRandom.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            Assert.assertEquals(data, data2);
        }
    }
}
