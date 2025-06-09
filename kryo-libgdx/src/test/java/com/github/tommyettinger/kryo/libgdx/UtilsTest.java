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

import com.badlogic.gdx.utils.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {
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
    public void testSnapshotArray() {
        Kryo kryo = new Kryo();
        kryo.register(SnapshotArray.class, new SnapshotArraySerializer());

        SnapshotArray<String> data = SnapshotArray.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            SnapshotArray<?> data2 = kryo.readObject(input, SnapshotArray.class);
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

    @Test
    public void testQueue() {
        Kryo kryo = new Kryo();
        kryo.register(Queue.class, new QueueSerializer());

        Queue<String> data = new Queue<>(9);
        for (String s : new String[]{"Hello", "World", "!", "I", "am", "a", "test", "!", "yay"}) {
            data.addLast(s);
        }

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Queue<?> data2 = kryo.readObject(input, Queue.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongQueue() {
        Kryo kryo = new Kryo();
        kryo.register(LongQueue.class, new LongQueueSerializer());

        LongQueue data = new LongQueue();
        for(long item : new long[]{-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0}) {
            data.addLast(item);
        }

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongQueue data2 = kryo.readObject(input, LongQueue.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectSet() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectSet.class, new ObjectSetSerializer());

        ObjectSet<String> data = ObjectSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectSet<?> data2 = kryo.readObject(input, ObjectSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(OrderedSet.class, new OrderedSetSerializer());

        OrderedSet<String> data = OrderedSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            OrderedSet<?> data2 = kryo.readObject(input, OrderedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.orderedItems(), data2.orderedItems());
        }
    }

    @Test
    public void testIntSet() {
        Kryo kryo = new Kryo();
        kryo.register(IntSet.class, new IntSetSerializer());

        IntSet data = IntSet.with(-123, 0, 456, 0, 1, -1, 0x80000000);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntSet data2 = kryo.readObject(input, IntSet.class);
            Assert.assertEquals(data, data2);
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
    public void testObjectMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectMap.class, new ObjectMapSerializer());

        ObjectMap<String, Integer> data = new ObjectMap<>();
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
            ObjectMap<?,?> data2 = kryo.readObject(input, ObjectMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(OrderedMap.class, new OrderedMapSerializer());

        OrderedMap<String, Integer> data = new OrderedMap<>();
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
            OrderedMap<?,?> data2 = kryo.readObject(input, OrderedMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectFloatMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectFloatMap.class, new ObjectFloatMapSerializer());

        ObjectFloatMap<String> data = new ObjectFloatMap<>();
        data.put("Cthulhu", -123456.1f);
        data.put("lies", Float.MIN_VALUE);
        data.put("deep", 456789012);
        data.put("in", Float.NEGATIVE_INFINITY);
        data.put("Rl'yeh", Float.POSITIVE_INFINITY);
        data.put("dreaming", 1);
        data.put("of", -1);
        data.put("waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectFloatMap<?> data2 = kryo.readObject(input, ObjectFloatMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectIntMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectIntMap.class, new ObjectIntMapSerializer());

        ObjectIntMap<String> data = new ObjectIntMap<>();
        data.put("Cthulhu", -123456);
        data.put("lies", Integer.MIN_VALUE);
        data.put("deep", 456789012);
        data.put("in", Integer.MAX_VALUE);
        data.put("Rl'yeh", 1111111111);
        data.put("dreaming", 1);
        data.put("of", -1);
        data.put("waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectIntMap<?> data2 = kryo.readObject(input, ObjectIntMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectLongMap() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectLongMap.class, new ObjectLongMapSerializer());

        ObjectLongMap<String> data = new ObjectLongMap<>();
        data.put("Cthulhu", -123456);
        data.put("lies", Long.MIN_VALUE);
        data.put("deep", 456789012);
        data.put("in", Long.MAX_VALUE);
        data.put("Rl'yeh", 1111111111111111L);
        data.put("dreaming", 1);
        data.put("of", -1);
        data.put("waffles", 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectLongMap<?> data2 = kryo.readObject(input, ObjectLongMap.class);
            Assert.assertEquals(data, data2);
        }
    }
}
