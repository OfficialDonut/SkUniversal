package us._donut_.skuniversal.slimefun;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.inventory.ItemStack;

public class SlimefunRegister {

    public SlimefunRegister() {
        //Expressions
        Skript.registerExpression(ExprItemNames.class, String.class, ExpressionType.SIMPLE, "[the] [names of] [all] [the] Slimefun items");
        Skript.registerExpression(ExprSlimefunItem.class, ItemStack.class, ExpressionType.SIMPLE, "[the] Slimefun item [(named|with name)] %string%");
    }
}
