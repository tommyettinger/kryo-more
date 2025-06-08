/*
 * Copyright (c) 2022-2024 See AUTHORS file.
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

package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Assert;
import org.junit.Test;

public class GdxTest {
    @Test
    public void testAffine2() {
        Kryo kryo = new Kryo();
        kryo.register(Affine2.class, new Affine2Serializer());

        Affine2 data = new Affine2();

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Affine2 data2 = kryo.readObject(input, Affine2.class);
            // Affine2 does not implement equals().
//            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.m00, data2.m00, 0.00001f);
            Assert.assertEquals(data.m01, data2.m01, 0.00001f);
            Assert.assertEquals(data.m02, data2.m02, 0.00001f);
            Assert.assertEquals(data.m10, data2.m10, 0.00001f);
            Assert.assertEquals(data.m11, data2.m11, 0.00001f);
            Assert.assertEquals(data.m12, data2.m12, 0.00001f);
        }
    }

    @Test
    public void testArrayMap() {
        Kryo kryo = new Kryo();
        kryo.register(ArrayMap.class, new ArrayMapSerializer());

        ArrayMap<String, Integer> data = new ArrayMap<>();
        data.put("Cthulhu", -123456);
        data.put("lies", Integer.MIN_VALUE);
        data.put("deep", 456789012);
        data.put("in", 0);
        data.put("Rl'yeh", 1111);
        data.put("dreaming", 1);
        data.put("of", -1);
        data.put("waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ArrayMap<?,?> data2 = kryo.readObject(input, ArrayMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testArray() {
        Kryo kryo = new Kryo();
        kryo.register(Array.class, new ArraySerializer());

        Array<String> data = Array.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Array<?> data2 = kryo.readObject(input, Array.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testDelayedRemovalArray() {
        Kryo kryo = new Kryo();
        kryo.register(DelayedRemovalArray.class, new DelayedRemovalArraySerializer());

        DelayedRemovalArray<String> data = DelayedRemovalArray.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            DelayedRemovalArray<?> data2 = kryo.readObject(input, DelayedRemovalArray.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testBooleanArray() {
        Kryo kryo = new Kryo();
        kryo.register(BooleanArray.class, new BooleanArraySerializer());

        BooleanArray data = BooleanArray.with(true, false, false, true, false, true, false);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            BooleanArray data2 = kryo.readObject(input, BooleanArray.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testBoundingBox() {
        Kryo kryo = new Kryo();
        kryo.register(BoundingBox.class, new BoundingBoxSerializer());

        Vector3[] testing = {
                new Vector3(0, 0, 0),
                new Vector3(-0f, -0f, -0f),
                new Vector3(1, 0, 0),
                new Vector3(0, 1, 0),
                new Vector3(0, 0, 1),
                new Vector3(1, 1, 1),
                new Vector3(-1, -1, -1),
                new Vector3(9999.9f, 9999.9f, 9999.9f),
                new Vector3(9999.9f, -9999.9f, 0),
                new Vector3(Float.NaN, Float.NaN, Float.NaN),
                new Vector3(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN),
                new Vector3(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE),
                new Vector3(-Float.MIN_VALUE, -Float.MIN_VALUE, -Float.MIN_VALUE),
                new Vector3(0x7FF.FFp-5f, 0x7FF.FFp-5f, 0x7FF.FFp-5f), new Vector3(-0x7FF.FFp-5f, -0x7FF.FFp-5f, -0x7FF.FFp-5f)};

        for (Vector3 origin : testing) {
            for (Vector3 direction : testing) {
                BoundingBox data = new BoundingBox(origin, direction);
                Output output = new Output(32, -1);
                kryo.writeObject(output, data);
                byte[] bytes = output.toBytes();
                try (Input input = new Input(bytes)) {
                    BoundingBox data2 = kryo.readObject(input, BoundingBox.class);
                    // BoundingBox does not implement equals().
//                    Assert.assertEquals(data, data2);
                    Assert.assertEquals(data.min, data2.min);
                    Assert.assertEquals(data.max, data2.max);
                }
            }
        }
    }

    @Test
    public void testByteArray() {
        Kryo kryo = new Kryo();
        kryo.register(ByteArray.class, new ByteArraySerializer());

        ByteArray data = ByteArray.with(new byte[]{-123, 0, 45, 0, 1, -1, 0});

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ByteArray data2 = kryo.readObject(input, ByteArray.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCharArray() {
        Kryo kryo = new Kryo();
        kryo.register(CharArray.class, new CharArraySerializer());

        CharArray data = CharArray.with("Hello, World!".toCharArray());

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CharArray data2 = kryo.readObject(input, CharArray.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFloatArray() {
        Kryo kryo = new Kryo();
        kryo.register(FloatArray.class, new FloatArraySerializer());

        FloatArray data = FloatArray.with(-123.123f, 0f, 456.456f, 0, 1f, -1f, 0.000001f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FloatArray data2 = kryo.readObject(input, FloatArray.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntArray() {
        Kryo kryo = new Kryo();
        kryo.register(IntArray.class, new IntArraySerializer());

        IntArray data = IntArray.with(-123, 0, 456, 0, 1, -1, 0x80000000);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntArray data2 = kryo.readObject(input, IntArray.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongArray() {
        Kryo kryo = new Kryo();
        kryo.register(LongArray.class, new LongArraySerializer());

        LongArray data = LongArray.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongArray data2 = kryo.readObject(input, LongArray.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testShortArray() {
        Kryo kryo = new Kryo();
        kryo.register(ShortArray.class, new ShortArraySerializer());

        ShortArray data = ShortArray.with(new short[]{-123, 0, 456, 0, 1, -1, 0});

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ShortArray data2 = kryo.readObject(input, ShortArray.class);
            Assert.assertEquals(data, data2);
        }
    }
}
