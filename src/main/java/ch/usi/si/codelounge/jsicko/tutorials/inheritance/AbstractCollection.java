/*
 * Copyright (C) 2018 Andrea Mocci and CodeLounge https://codelounge.si.usi.ch
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
import static ch.usi.si.codelounge.jsicko.Contract.old;
import static ch.usi.si.codelounge.jsicko.ContractUtils.*;

public abstract class AbstractCollection<T> implements Contract {

    protected AbstractCollection() {}

    @Invariant
    @Pure
    protected boolean size_non_negative() {
        return size() >= 0;
    }

    @Invariant
    @Pure
    protected boolean empty_iff_size_0() {
        return iff(size() == 0, isEmpty());
    }

    @Pure
    public abstract boolean isEmpty();

    @Pure
    public abstract int size();

    @Requires("element_not_null")
    @Pure
    public abstract boolean contains(T element);

    @Pure
    protected boolean element_not_null(T element) {
        return element != null;
    }

    @Pure
    protected boolean contains_element(T element) {
        return contains(element);
    }

    @Pure
    protected boolean returns_true_if_old_contains(boolean returns, T element) {
        return returns == old(this).contains(element);
    }

    @Pure
    protected boolean size_decreases_if_contained(T element) {
        return implies(old(this).contains_element(element), this.size() == old(this).size() - 1) &&
                implies(!old(this).contains_element(element), this.size() == old(this).size());
    }

    @Pure
    protected boolean size_may_increase(T element) {
        return this.size() >= old(this).size();
    }

    @Requires("element_not_null")
    @Ensures({"contains_element", "size_may_increase"})
    public abstract void add(T element);

    @Requires("element_not_null")
    @Ensures({"size_decreases_if_contained", "returns_true_if_old_contains"})
    public abstract boolean remove(T element);


}
