/*
 * Copyright (c) 2020-2024 See AUTHORS file.
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

package com.github.tommyettinger.kryo.cringe;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.cringe.GdxRandom;
import com.github.tommyettinger.cringe.WeightedTable;

/**
 * Needs the concrete {@link GdxRandom} subclass that a WeightedTable can use registered.
 * It is recommended to use {@link com.github.tommyettinger.cringe.RandomAce320} as the
 * only GdxRandom for this type, because others might not deserialize correctly.
 */
public class WeightedTableSerializer extends Serializer<WeightedTable> {
    public WeightedTableSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final WeightedTable data) {
        output.writeString(data.stringSerialize());
    }

    @Override
    public WeightedTable read(final Kryo kryo, final Input input, final Class<? extends WeightedTable> dataClass) {
        return new WeightedTable().stringDeserialize(input.readString());
    }

    @Override
    public WeightedTable copy(Kryo kryo, WeightedTable original) {
        return new WeightedTable(original);
    }
}
