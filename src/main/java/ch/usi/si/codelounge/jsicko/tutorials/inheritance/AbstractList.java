/*
 * Copyright (C) 2019 Andrea Mocci and CodeLounge https://codelounge.si.usi.ch
 *
 * This file is part of jSicko - Java SImple Contract checKer.
 *
 *  jSicko is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 * jSicko is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSicko.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package ch.usi.si.codelounge.jsicko.tutorials.inheritance;

import ch.usi.si.codelounge.jsicko.Contract;

import java.util.stream.IntStream;

import static ch.usi.si.codelounge.jsicko.Contract.old;
import static ch.usi.si.codelounge.jsicko.ContractUtils.*;

public abstract class AbstractList<T> extends AbstractCollection<T> implements Contract {

    protected AbstractList() {}

    @Override
    boolean supports_null_elements() {
        return true;
    }

    @Pure
    protected boolean size_increases() {
        return this.size() == old(this).size() + 1;
    }

    @Pure
    protected boolean size_decreased_iff_contained(T element) {
        return implies(old(this).contains(element),
                () -> this.size() == old(this).size() - 1,
                () -> this.size() == old(this).size());
    }

    boolean index_is_valid(int index) {
        return index >= 0 && index < size();
    }

    @Pure
    @Requires("index_is_valid")
    public abstract T get(int index);

    @Override
    @Ensures("size_increases")
    public abstract void add(T element);

    @Pure
    private boolean contained_at_most_once(T element) {
        return IntStream.range(0, old(this).size()).filter((int i) -> old(this).get(i) == element).count() <= 1;
    }

    @Pure
    protected boolean if_contained_once_not_contained_anymore(T element) {
        return implies(contained_at_most_once(element),
                () -> !contains(element),
                () -> contains(element));
    }

    @Override
    @Ensures({"size_decreased_iff_contained", "if_contained_once_not_contained_anymore"})
    public abstract boolean remove(T element);

    @Pure
    boolean greater_size_than_other(AbstractCollection<T> other) {
        return this.size() > other.size();
    }

    @Override
    @Requires({"other_non_null", "other_non_empty", "greater_size_than_other"})
    public abstract AbstractList<T> copyFrom(AbstractCollection<T> other);
}
