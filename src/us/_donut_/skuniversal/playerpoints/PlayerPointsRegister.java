package us._donut_.skuniversal.playerpoints;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class PlayerPointsRegister {

    public static void registerPlayerPoints() {
        //Expressions
        Skript.registerExpression(ExprPointsBalance.class, Number.class, ExpressionType.COMBINED, "[the] [Player][ ]Point[s] [bal[ance]] of %offlineplayer%", "%offlineplayer%'s [Player][ ]Point[s] [bal[ance]]");
    }
}