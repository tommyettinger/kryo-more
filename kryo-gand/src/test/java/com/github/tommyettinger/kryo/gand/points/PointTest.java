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

package com.github.tommyettinger.kryo.gand.points;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.crux.PointPair;
import com.github.tommyettinger.gand.points.PointF2;
import com.github.tommyettinger.gand.points.PointF3;
import com.github.tommyettinger.gand.points.PointI2;
import com.github.tommyettinger.gand.points.PointI3;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class PointTest {

    @Test
    public void testPointF2() {
        Kryo kryo = new Kryo();
        kryo.register(PointF2.class, new PointF2Serializer());

        PointF2 data = new PointF2(1.23f, 2.34f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
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

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointF3 data2 = kryo.readObject(input, PointF3.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testPointI2() {
        Kryo kryo = new Kryo();
        kryo.register(PointI2.class, new PointI2Serializer());

        PointI2 data = new PointI2(12, 23);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
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

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointI3 data2 = kryo.readObject(input, PointI3.class);
            Assert.assertEquals(data, data2);
        }
    }
    @Test
    public void testPointPair() {
        Kryo kryo = new Kryo();
        kryo.register(PointI2.class, new PointI2Serializer());
        kryo.register(PointPair.class, new PointPairSerializer());
        PointPair<PointI2> data = new PointPair<>(new PointI2(0, 0), new PointI2(1, 1));

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            PointPair<?> data2 = kryo.readObject(input, PointPair.class);
            Assert.assertEquals(data, data2);
        }
    }

}
