// This file is part of the Simple Lisp Interpreter.
//
// The Simple Lisp Interpreter is free software; you can redistribute it and/or modify it under the
// terms of the GNU General Public License as published by the Free Software Foundation; either
// version 2 of the License, or (at your option) any later version.
//
// The Simpular Program Interpreter is distributed in the hope that it will be useful, but WITHOUT
// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE. See the GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along with the Simpular Program
// Interpreter; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
// Boston, MA 02111-1307 USA
//
// (C) David James Pearce, 2005.

package com.hjwylde.uni.swen221.lab07.org.simplelisp.interpreter;

import java.util.HashMap;

/*
 * Code for Laboratory 7, SWEN 221 Name: Henry J. Wylde Usercode: wyldehenr ID: 300224283
 */

public final class LispQuote implements LispExpr {

    public final LispExpr expr;

    public LispQuote(LispExpr e) {
        expr = e;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LispQuote))
            return false;
        LispQuote q = (LispQuote) o;
        return expr.equals(q.expr);
    }

    @Override
    public LispExpr evaluate(HashMap<String, LispExpr> locals, HashMap<String, LispExpr> globals) {
        return expr;
    }

    public LispExpr getExpr() {
        return expr;
    }

    @Override
    public int hashCode() {
        return expr.hashCode();
    }

    @Override
    public String toString() {
        return "'" + expr.toString();
    }
}
