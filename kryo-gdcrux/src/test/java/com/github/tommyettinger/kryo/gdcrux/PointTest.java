/*
 * Copyright (c) 2024 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.tommyettinger.kryo.gdcrux;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.crux.PointPair;
import com.github.tommyettinger.gdcrux.*;
import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void testPointF2() {
        Kryo kryo = new Kryo();
        kryo.register(PointF2.class, new PointF2Serializer());

        PointF2 data = new PointF2(1.23f, 2.34f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointF2 data2 = kryo.readObject(input, PointF2.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointF3() {
        Kryo kryo = new Kryo();
        kryo.register(PointF3.class, new PointF3Serializer());

        PointF3 data = new PointF3(1.23f, 2.34f, 3.45f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointF3 data2 = kryo.readObject(input, PointF3.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointF4() {
        Kryo kryo = new Kryo();
        kryo.register(PointF4.class, new PointF4Serializer());

        PointF4 data = new PointF4(1.23f, 2.34f, 3.45f, 4.56f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointF4 data2 = kryo.readObject(input, PointF4.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointF5() {
        Kryo kryo = new Kryo();
        kryo.register(PointF5.class, new PointF5Serializer());

        PointF5 data = new PointF5(1.23f, 2.34f, 3.45f, 4.56f, 5.67f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointF5 data2 = kryo.readObject(input, PointF5.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointF6() {
        Kryo kryo = new Kryo();
        kryo.register(PointF6.class, new PointF6Serializer());

        PointF6 data = new PointF6(1.23f, 2.34f, 3.45f, 4.56f, 5.67f, 6.78f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointF6 data2 = kryo.readObject(input, PointF6.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testVector5() {
        Kryo kryo = new Kryo();
        kryo.register(Vector5.class, new Vector5Serializer());

        Vector5 data = new Vector5(1.23f, 2.34f, 3.45f, 4.56f, 5.67f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Vector5 data2 = kryo.readObject(input, Vector5.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testVector6() {
        Kryo kryo = new Kryo();
        kryo.register(Vector6.class, new Vector6Serializer());

        Vector6 data = new Vector6(1.23f, 2.34f, 3.45f, 4.56f, 5.67f, 6.78f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Vector6 data2 = kryo.readObject(input, Vector6.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointI2() {
        Kryo kryo = new Kryo();
        kryo.register(PointI2.class, new PointI2Serializer());

        PointI2 data = new PointI2(12, 23);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointI2 data2 = kryo.readObject(input, PointI2.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointI3() {
        Kryo kryo = new Kryo();
        kryo.register(PointI3.class, new PointI3Serializer());

        PointI3 data = new PointI3(12, 23, 34);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointI3 data2 = kryo.readObject(input, PointI3.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointI4() {
        Kryo kryo = new Kryo();
        kryo.register(PointI4.class, new PointI4Serializer());

        PointI4 data = new PointI4(12, 23, 34, 45);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointI4 data2 = kryo.readObject(input, PointI4.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointI5() {
        Kryo kryo = new Kryo();
        kryo.register(PointI5.class, new PointI5Serializer());

        PointI5 data = new PointI5(12, 23, 34, 45, 56);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointI5 data2 = kryo.readObject(input, PointI5.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointI6() {
        Kryo kryo = new Kryo();
        kryo.register(PointI6.class, new PointI6Serializer());

        PointI6 data = new PointI6(12, 23, 34, 45, 56, 67);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointI6 data2 = kryo.readObject(input, PointI6.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointPair() {
        Kryo kryo = new Kryo();
        kryo.register(PointI2.class, new PointI2Serializer());
        kryo.register(PointPair.class, new PointPairSerializer());
        PointPair<PointI2> data = new PointPair<>(new PointI2(0, 0), new PointI2(1, 1));

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointPair<?> data2 = kryo.readObject(input, PointPair.class);
            Assert.assertEquals(data, data2);
        }
    }

}
