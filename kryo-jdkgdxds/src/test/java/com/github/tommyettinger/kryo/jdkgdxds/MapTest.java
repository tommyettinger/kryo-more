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

package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.ds.*;
import com.github.tommyettinger.function.ObjPredicate;
import com.github.tommyettinger.function.ObjToSameFunction;
import org.junit.Assert;
import org.junit.Test;

public class MapTest {
    @Test
    public void testLongObjectMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongObjectMap.class, new LongObjectMapSerializer());

        LongObjectMap<Float> data = LongObjectMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongObjectMap<?> data2 = kryo.readObject(input, LongObjectMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongObjectOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongObjectOrderedMap.class, new LongObjectOrderedMapSerializer());

        LongObjectOrderedMap<Float> data = LongObjectOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongObjectOrderedMap<?> data2 = kryo.readObject(input, LongObjectOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongFloatMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongFloatMap.class, new LongFloatMapSerializer());

        LongFloatMap data = LongFloatMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongFloatMap data2 = kryo.readObject(input, LongFloatMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongFloatOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongFloatOrderedMap.class, new LongFloatOrderedMapSerializer());

        LongFloatOrderedMap data = LongFloatOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongFloatOrderedMap data2 = kryo.readObject(input, LongFloatOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testLongIntMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongIntMap.class, new LongIntMapSerializer());

        LongIntMap data = LongIntMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongIntMap data2 = kryo.readObject(input, LongIntMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongIntOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongIntOrderedMap.class, new LongIntOrderedMapSerializer());

        LongIntOrderedMap data = LongIntOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongIntOrderedMap data2 = kryo.readObject(input, LongIntOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }
    @Test
    public void testLongLongMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongLongMap.class, new LongLongMapSerializer());

        LongLongMap data = LongLongMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongLongMap data2 = kryo.readObject(input, LongLongMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongLongOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongLongOrderedMap.class, new LongLongOrderedMapSerializer());

        LongLongOrderedMap data = LongLongOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongLongOrderedMap data2 = kryo.readObject(input, LongLongOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testIntObjectMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntObjectMap.class, new IntObjectMapSerializer());

        IntObjectMap<Float> data = IntObjectMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntObjectMap<?> data2 = kryo.readObject(input, IntObjectMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntObjectOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntObjectOrderedMap.class, new IntObjectOrderedMapSerializer());

        IntObjectOrderedMap<Float> data = IntObjectOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntObjectOrderedMap<?> data2 = kryo.readObject(input, IntObjectOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntFloatMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntFloatMap.class, new IntFloatMapSerializer());

        IntFloatMap data = IntFloatMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntFloatMap data2 = kryo.readObject(input, IntFloatMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntFloatOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntFloatOrderedMap.class, new IntFloatOrderedMapSerializer());

        IntFloatOrderedMap data = IntFloatOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntFloatOrderedMap data2 = kryo.readObject(input, IntFloatOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }
    
    @Test
    public void testIntIntMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntIntMap.class, new IntIntMapSerializer());

        IntIntMap data = IntIntMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntIntMap data2 = kryo.readObject(input, IntIntMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntIntOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntIntOrderedMap.class, new IntIntOrderedMapSerializer());

        IntIntOrderedMap data = IntIntOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntIntOrderedMap data2 = kryo.readObject(input, IntIntOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testIntLongMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntLongMap.class, new IntLongMapSerializer());

        IntLongMap data = IntLongMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntLongMap data2 = kryo.readObject(input, IntLongMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntLongOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntLongOrderedMap.class, new IntLongOrderedMapSerializer());

        IntLongOrderedMap data = IntLongOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntLongOrderedMap data2 = kryo.readObject(input, IntLongOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testEnumMap() {
        Kryo kryo = new Kryo();
        kryo.register(Character.UnicodeScript.class);
        kryo.register(EnumMap.class, new EnumMapSerializer());

        EnumMap<Integer> data = EnumMap.with(Character.UnicodeScript.LATIN, -123456, Character.UnicodeScript.ARABIC, Integer.MIN_VALUE,
                Character.UnicodeScript.LAO, 456789012, Character.UnicodeScript.ARMENIAN, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumMap<?> data2 = kryo.readObject(input, EnumMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnumOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(Character.UnicodeScript.class);
        kryo.register(EnumOrderedMap.class, new EnumOrderedMapSerializer());

        EnumOrderedMap<Integer> data = EnumOrderedMap.with(Character.UnicodeScript.LATIN, -123456, Character.UnicodeScript.ARABIC, Integer.MIN_VALUE,
                Character.UnicodeScript.LAO, 456789012, Character.UnicodeScript.ARMENIAN, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumOrderedMap<?> data2 = kryo.readObject(input, EnumOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnumIntMap() {
        Kryo kryo = new Kryo();
        kryo.register(Character.UnicodeScript.class);
        kryo.register(EnumIntMap.class, new EnumIntMapSerializer());

        EnumIntMap data = EnumIntMap.with(Character.UnicodeScript.LATIN, -123456, Character.UnicodeScript.ARABIC, Integer.MIN_VALUE,
                Character.UnicodeScript.LAO, 456789012, Character.UnicodeScript.ARMENIAN, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumIntMap data2 = kryo.readObject(input, EnumIntMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnumIntOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(Character.UnicodeScript.class);
        kryo.register(EnumIntOrderedMap.class, new EnumIntOrderedMapSerializer());

        EnumIntOrderedMap data = EnumIntOrderedMap.with(Character.UnicodeScript.LATIN, -123456, Character.UnicodeScript.ARABIC, Integer.MIN_VALUE,
                Character.UnicodeScript.LAO, 456789012, Character.UnicodeScript.ARMENIAN, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumIntOrderedMap data2 = kryo.readObject(input, EnumIntOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnumLongMap() {
        Kryo kryo = new Kryo();
        kryo.register(Character.UnicodeScript.class);
        kryo.register(EnumLongMap.class, new EnumLongMapSerializer());

        EnumLongMap data = EnumLongMap.with(Character.UnicodeScript.LATIN, -123456, Character.UnicodeScript.ARABIC, Integer.MIN_VALUE,
                Character.UnicodeScript.LAO, 456789012, Character.UnicodeScript.ARMENIAN, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumLongMap data2 = kryo.readObject(input, EnumLongMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnumLongOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(Character.UnicodeScript.class);
        kryo.register(EnumLongOrderedMap.class, new EnumLongOrderedMapSerializer());

        EnumLongOrderedMap data = EnumLongOrderedMap.with(Character.UnicodeScript.LATIN, -123456, Character.UnicodeScript.ARABIC, Integer.MIN_VALUE,
                Character.UnicodeScript.LAO, 456789012, Character.UnicodeScript.ARMENIAN, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumLongOrderedMap data2 = kryo.readObject(input, EnumLongOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnumFloatMap() {
        Kryo kryo = new Kryo();
        kryo.register(Character.UnicodeScript.class);
        kryo.register(EnumFloatMap.class, new EnumFloatMapSerializer());

        EnumFloatMap data = EnumFloatMap.with(Character.UnicodeScript.LATIN, -123456, Character.UnicodeScript.ARABIC, Integer.MIN_VALUE,
                Character.UnicodeScript.LAO, 456789012, Character.UnicodeScript.ARMENIAN, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumFloatMap data2 = kryo.readObject(input, EnumFloatMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnumFloatOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(Character.UnicodeScript.class);
        kryo.register(EnumFloatOrderedMap.class, new EnumFloatOrderedMapSerializer());

        EnumFloatOrderedMap data = EnumFloatOrderedMap.with(Character.UnicodeScript.LATIN, -123456, Character.UnicodeScript.ARABIC, Integer.MIN_VALUE,
                Character.UnicodeScript.LAO, 456789012, Character.UnicodeScript.ARMENIAN, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumFloatOrderedMap data2 = kryo.readObject(input, EnumFloatOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectObjectMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectObjectMap.class, new ObjectObjectMapSerializer());

        ObjectObjectMap<String, Integer> data = ObjectObjectMap.with("Cthulhu", -123456, "lies", Integer.MIN_VALUE,
                "deep", 456789012, "in", 0, "Rl'yeh", 1111, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectObjectMap<?,?> data2 = kryo.readObject(input, ObjectObjectMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectObjectOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectObjectOrderedMap.class, new ObjectObjectOrderedMapSerializer());

        ObjectObjectOrderedMap<String, Integer> data = ObjectObjectOrderedMap.with("Cthulhu", -123456, "lies", Integer.MIN_VALUE,
                "deep", 456789012, "in", 0, "Rl'yeh", 1111, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectObjectOrderedMap<?,?> data2 = kryo.readObject(input, ObjectObjectOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectIntMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectIntMap.class, new ObjectIntMapSerializer());

        ObjectIntMap<String> data = ObjectIntMap.with("Cthulhu", -123456, "lies", Integer.MIN_VALUE,
                "deep", 456789012, "in", 0, "Rl'yeh", 1111, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectIntMap<?> data2 = kryo.readObject(input, ObjectIntMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectIntOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectIntOrderedMap.class, new ObjectIntOrderedMapSerializer());

        ObjectIntOrderedMap<String> data = ObjectIntOrderedMap.with("Cthulhu", -123456, "lies", Integer.MIN_VALUE,
                "deep", 456789012, "in", 0, "Rl'yeh", 1111, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectIntOrderedMap<?> data2 = kryo.readObject(input, ObjectIntOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testObjectLongMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectLongMap.class, new ObjectLongMapSerializer());

        ObjectLongMap<String> data = ObjectLongMap.with("Cthulhu", -1234567890L, "lies", 0L, "deep",
                4567890123456789L, "in", 0, "Rl'yeh", 1L, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectLongMap<?> data2 = kryo.readObject(input, ObjectLongMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectLongOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectLongOrderedMap.class, new ObjectLongOrderedMapSerializer());

        ObjectLongOrderedMap<String> data = ObjectLongOrderedMap.with("Cthulhu", -1234567890L, "lies", 0L, "deep",
                4567890123456789L, "in", 0, "Rl'yeh", 1L, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectLongOrderedMap<?> data2 = kryo.readObject(input, ObjectLongOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }
    
    @Test
    public void testObjectFloatMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectFloatMap.class, new ObjectFloatMapSerializer());

        ObjectFloatMap<String> data = ObjectFloatMap.with("Cthulhu", -123456.789f, "lies", 0f, "deep",
                4.56789f, "in", 0.0001f, "Rl'yeh", 1f, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectFloatMap<?> data2 = kryo.readObject(input, ObjectFloatMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectFloatOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectFloatOrderedMap.class, new ObjectFloatOrderedMapSerializer());

        ObjectFloatOrderedMap<String> data = ObjectFloatOrderedMap.with("Cthulhu", -123456.789f, "lies", 0f, "deep",
                4.56789f, "in", 0.0001f, "Rl'yeh", 1f, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectFloatOrderedMap<?> data2 = kryo.readObject(input, ObjectFloatOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testCaseInsensitiveMap() {
        Kryo kryo = new Kryo();
        kryo.register(CaseInsensitiveMap.class, new CaseInsensitiveMapSerializer());

        CaseInsensitiveMap<Integer> data = CaseInsensitiveMap.with("Cthulhu", -123456, "lies", Integer.MIN_VALUE,
                "deep", 456789012, "in", 0, "Rl'yeh", 1111, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CaseInsensitiveMap<?> data2 = kryo.readObject(input, CaseInsensitiveMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCaseInsensitiveOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(CaseInsensitiveOrderedMap.class, new CaseInsensitiveOrderedMapSerializer());

        CaseInsensitiveOrderedMap<Integer> data = CaseInsensitiveOrderedMap.with("Cthulhu", -123456, "lies", Integer.MIN_VALUE,
                "deep", 456789012, "in", 0, "Rl'yeh", 1111, "dreaming", 1, "of", -1, "waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CaseInsensitiveOrderedMap<?> data2 = kryo.readObject(input, CaseInsensitiveOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFilteredStringMap() {
        CharFilter filter = CharFilter.getOrCreate("LettersOnlyIgnoreCase", Character::isLetter, Character::toUpperCase);
        Kryo kryo = new Kryo();
        kryo.register(FilteredStringMap.class, new FilteredStringMapSerializer());

        FilteredStringMap<Integer> data = FilteredStringMap.with(filter, "Hello", -123456, "World", Integer.MIN_VALUE,
                "!", 456789012, "YES", 0, "HELLO", 1111, "WORLD", 1, "!", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FilteredStringMap<?> data2 = kryo.readObject(input, FilteredStringMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFilteredStringOrderedMap() {
        CharFilter filter = CharFilter.getOrCreate("LettersOnlyIgnoreCase", Character::isLetter, Character::toUpperCase);
        Kryo kryo = new Kryo();
        kryo.register(FilteredStringOrderedMap.class, new FilteredStringOrderedMapSerializer());

        FilteredStringOrderedMap<Integer> data = FilteredStringOrderedMap.with(filter, "Hello", -123456, "World", Integer.MIN_VALUE,
                "!", 456789012, "YES", 0, "HELLO", 1111, "WORLD", 1, "!", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FilteredStringOrderedMap<?> data2 = kryo.readObject(input, FilteredStringOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testFilteredIterableMap() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjPredicate.class);
        kryo.register(ObjToSameFunction.class);
        kryo.register(ObjectList.class, new ObjectListSerializer());
        kryo.register(FilteredIterableMap.class, new FilteredIterableMapSerializer());

        FilteredIterableMap<String, ObjectList<String>, Integer> data = FilteredIterableMap.with(
                (String s) -> s.length() > 3, String::toUpperCase,
                ObjectList.with("zzz", "bee", "binturong"), 1234, new Object[]{
                ObjectList.with("hm?", "bee", "BINTURONG"), -5678,
                ObjectList.with(":D", "bee", "Aardvark", "bandicoot"), Integer.MIN_VALUE}
        );

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FilteredIterableMap<?,?,?> data2 = kryo.readObject(input, FilteredIterableMap.class);
            Assert.assertEquals(data, data2);
        }
    }
    
    @Test
    public void testFilteredIterableOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjPredicate.class);
        kryo.register(ObjToSameFunction.class);
        kryo.register(ObjectList.class, new ObjectListSerializer());
        kryo.register(FilteredIterableOrderedMap.class, new FilteredIterableOrderedMapSerializer());

        FilteredIterableOrderedMap<String, ObjectList<String>, Integer> data = FilteredIterableOrderedMap.with(
                (String s) -> s.length() > 3, String::toUpperCase,
                ObjectList.with("zzz", "bee", "binturong"), 1234, new Object[]{
                ObjectList.with("hm?", "bee", "BINTURONG"), -5678,
                ObjectList.with(":D", "bee", "Aardvark", "bandicoot"), Integer.MIN_VALUE }
        );

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FilteredIterableOrderedMap<?,?,?> data2 = kryo.readObject(input, FilteredIterableOrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

}
