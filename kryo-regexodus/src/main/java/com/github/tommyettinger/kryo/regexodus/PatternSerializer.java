/*
 * Copyright (c) 2022 See AUTHORS file.
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

import regexodus.Pattern;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Kryo {@link Serializer} for Regexodus {@link Pattern}s.
 */
public class PatternSerializer extends Serializer<Pattern> {

    public PatternSerializer() {
        setImmutable(true);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final Pattern pattern) {
        output.writeString(pattern.serializeToString());
    }

    @Override
    public Pattern read(final Kryo kryo, final Input input, final Class<? extends Pattern> patternClass) {
        return Pattern.deserializeFromString(input.readString());
    }
}