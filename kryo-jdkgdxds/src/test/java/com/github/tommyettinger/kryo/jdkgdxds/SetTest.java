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
import com.github.tommyettinger.function.ObjToObjFunction;
import com.github.tommyettinger.function.ObjToSameFunction;
import org.junit.Assert;
import org.junit.Test;

import java.lang.Character.UnicodeScript;

public class SetTest {
    @Test
    public void testIntSet() {
        Kryo kryo = new Kryo();
        kryo.register(IntSet.class, new IntSetSerializer());

        IntSet data = IntSet.with(-123, 0, 456, 0, 1, -1, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntSet data2 = kryo.readObject(input, IntSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(IntOrderedSet.class, new IntOrderedSetSerializer());

        IntOrderedSet data = IntOrderedSet.with(-123, 0, 456, 0, 1, -1, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntOrderedSet data2 = kryo.readObject(input, IntOrderedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testLongSet() {
        Kryo kryo = new Kryo();
        kryo.register(LongSet.class, new LongSetSerializer());

        LongSet data = LongSet.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongSet data2 = kryo.readObject(input, LongSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(LongOrderedSet.class, new LongOrderedSetSerializer());

        LongOrderedSet data = LongOrderedSet.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongOrderedSet data2 = kryo.readObject(input, LongOrderedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testEnumSet() {
        Kryo kryo = new Kryo();
        kryo.register(UnicodeScript.class);
        kryo.register(Enum.class);
        kryo.register(EnumSet.class, new EnumSetSerializer());

        EnumSet data = EnumSet.with(UnicodeScript.LATIN, UnicodeScript.ARABIC, UnicodeScript.LAO, UnicodeScript.ARMENIAN);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumSet data2 = kryo.readObject(input, EnumSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testEnumOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(UnicodeScript.class);
        kryo.register(Enum.class);
        kryo.register(EnumOrderedSet.class, new EnumOrderedSetSerializer());

        EnumOrderedSet data = EnumOrderedSet.with(UnicodeScript.LATIN, UnicodeScript.ARABIC, UnicodeScript.LAO, UnicodeScript.ARMENIAN);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumOrderedSet data2 = kryo.readObject(input, EnumOrderedSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
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
    public void testObjectOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjectOrderedSet.class, new ObjectOrderedSetSerializer());

        ObjectOrderedSet<String> data = ObjectOrderedSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectOrderedSet<?> data2 = kryo.readObject(input, ObjectOrderedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testCaseInsensitiveSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(CaseInsensitiveSet.class, new CaseInsensitiveSetSerializer());

        CaseInsensitiveSet data = CaseInsensitiveSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CaseInsensitiveSet data2 = kryo.readObject(input, CaseInsensitiveSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCaseInsensitiveOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(CaseInsensitiveOrderedSet.class, new CaseInsensitiveOrderedSetSerializer());

        CaseInsensitiveOrderedSet data = CaseInsensitiveOrderedSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CaseInsensitiveOrderedSet data2 = kryo.readObject(input, CaseInsensitiveOrderedSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testOffsetBitSet() {
        Kryo kryo = new Kryo();
        kryo.register(OffsetBitSet.class, new OffsetBitSetSerializer());

        OffsetBitSet data = new OffsetBitSet(-123, 500);
        data.addAll(new int[]{-123, 0, 456, 0, 1, -1, 0});

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            OffsetBitSet data2 = kryo.readObject(input, OffsetBitSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testNumberedSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(NumberedSet.class, new NumberedSetSerializer());

        NumberedSet<String> data = NumberedSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            NumberedSet<?> data2 = kryo.readObject(input, NumberedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testHolderSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjToObjFunction.class);
        kryo.register(HolderSet.class, new HolderSetSerializer());

        ObjToObjFunction<String, String> f = s -> s.split("\\s+")[0];
        HolderSet<String, String> data = HolderSet.with(f, "Hello World!", "I am", "a test!", "Yippee yay wahoo!");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            HolderSet<?, ?> data2 = kryo.readObject(input, HolderSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testHolderOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjToObjFunction.class);
        kryo.register(HolderOrderedSet.class, new HolderOrderedSetSerializer());

        ObjToObjFunction<String, String> f = s -> s.split("\\s+")[0];
        HolderOrderedSet<String, String> data = HolderOrderedSet.with(f, "Hello World!", "I am", "a test!", "Yippee yay wahoo!");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            HolderOrderedSet<?, ?> data2 = kryo.readObject(input, HolderOrderedSet.class);
            Assert.assertEquals(data, data2);
        }
    }


    @Test
    public void testFilteredStringSet() {
        CharFilter filter = CharFilter.getOrCreate("LettersOnlyIgnoreCase", Character::isLetter, Character::toUpperCase);
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(FilteredStringSet.class, new FilteredStringSetSerializer());

        FilteredStringSet data = FilteredStringSet.with(filter, "Hello", "World", "!", "YES", "HELLO", "WORLD", "!");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FilteredStringSet data2 = kryo.readObject(input, FilteredStringSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFilteredStringOrderedSet() {
        CharFilter filter = CharFilter.getOrCreate("LettersOnlyIgnoreCase", Character::isLetter, Character::toUpperCase);
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(FilteredStringOrderedSet.class, new FilteredStringOrderedSetSerializer());

        FilteredStringOrderedSet data = FilteredStringOrderedSet.with(filter, "Hello", "World", "!", "YES", "HELLO", "WORLD", "!");

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FilteredStringOrderedSet data2 = kryo.readObject(input, FilteredStringOrderedSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFilteredIterableSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjPredicate.class);
        kryo.register(ObjToSameFunction.class);
        kryo.register(ObjectList.class, new ObjectListSerializer());
        kryo.register(FilteredIterableSet.class, new FilteredIterableSetSerializer());

        FilteredIterableSet<String, Iterable<String>> data = FilteredIterableSet.with(
                (String s) -> s.length() > 3, String::toUpperCase,
                ObjectList.with("zzz", "bee", "binturong"),
                ObjectList.with("hm?", "bee", "BINTURONG"),
                ObjectList.with(":D", "bee", "Aardvark", "bandicoot")
        );

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FilteredIterableSet<?, ?> data2 = kryo.readObject(input, FilteredIterableSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFilteredIterableOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjPredicate.class);
        kryo.register(ObjToSameFunction.class);
        kryo.register(ObjectList.class, new ObjectListSerializer());
        kryo.register(FilteredIterableOrderedSet.class, new FilteredIterableOrderedSetSerializer());

        FilteredIterableOrderedSet<String, Iterable<String>> data = FilteredIterableOrderedSet.with(
                (String s) -> s.length() > 3, String::toUpperCase, new Iterable[]{
                ObjectList.with("zzz", "bee", "binturong"),
                ObjectList.with("hm?", "bee", "BINTURONG"),
                ObjectList.with(":D", "bee", "Aardvark", "bandicoot") }
        );

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FilteredIterableOrderedSet<?, ?> data2 = kryo.readObject(input, FilteredIterableOrderedSet.class);
            Assert.assertEquals(data, data2);
        }
    }
}
