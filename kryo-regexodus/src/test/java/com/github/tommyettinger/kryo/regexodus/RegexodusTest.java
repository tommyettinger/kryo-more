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

package com.github.tommyettinger.kryo.regexodus;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Assert;
import org.junit.Test;
import regexodus.Category;
import regexodus.Pattern;
import regexodus.REFlags;
import regexodus.ds.CharBitSet;

public class RegexodusTest {
    @Test
    public void testPattern() {
        Kryo kryo = new Kryo();
        kryo.register(Pattern.class, new PatternSerializer());

        Pattern data = Pattern.compile("[a-z0-9_\\p{Sc}]+", REFlags.IGNORE_CASE | REFlags.UNICODE);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Pattern data2 = kryo.readObject(input, Pattern.class);
            Assert.assertEquals(data.matches("Meow€€€"), data2.matches("Meow€€€"));
            Assert.assertEquals(data.matches("Meow, baby, meow."), data2.matches("Meow, baby, meow."));
            Assert.assertEquals(data, data2);
        }
    }


    @Test
    public void testCharBitSet() {
        Kryo kryo = new Kryo();
        kryo.register(CharBitSet.class, new CharBitSetSerializer());

        CharBitSet data = Category.LowercaseLetter.decompress();
        data.addAll("ATOZ €".toCharArray());

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CharBitSet data2 = kryo.readObject(input, CharBitSet.class);
            Assert.assertEquals(data, data2);
        }
    }

}
