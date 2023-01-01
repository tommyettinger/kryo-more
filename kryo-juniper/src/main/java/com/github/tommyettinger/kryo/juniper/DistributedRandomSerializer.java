/*
 * Copyright (c) 2022-2023 See AUTHORS file.
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

package com.github.tommyettinger.kryo.juniper;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.digital.Base;
import com.github.tommyettinger.random.Deserializer;
import com.github.tommyettinger.random.DistributedRandom;

/**
 * Kryo {@link Serializer} for juniper {@link DistributedRandom}s.
 * Internally, this stores the serialized String produced by {@link DistributedRandom#stringSerialize(Base)}.
 * This allows it to reliably store the Distribution and ReductionMode it needs.
 */
public class DistributedRandomSerializer extends Serializer<DistributedRandom> {

    public DistributedRandomSerializer() {
        setImmutable(false);
        setAcceptsNull(false);
    }

    @Override
    public void write(final Kryo kryo, final Output output, final DistributedRandom data) {
        output.writeString(data.stringSerialize(Base.BASE86));
    }

    @Override
    public DistributedRandom read(final Kryo kryo, final Input input, final Class<? extends DistributedRandom> dataClass) {
        DistributedRandom random = new DistributedRandom(0L);
        random.stringDeserialize(input.readString(), Base.BASE86);
        return random;
    }

    @Override
    public DistributedRandom copy(Kryo kryo, DistributedRandom original) {
        return original.copy();
    }
}