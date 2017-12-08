package us._donut_.skuniversal.minepacks;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.inventory.ItemStack;

public class MinePacksRegister {

    public static void registerMinePacks() {
        //Effects
        Skript.registerEffect(EffOpenBackpack.class, "open [an] editable %boolean% (back|mine)pack of %player% to %player%", "open editable %boolean% %player%'s (back|mine)pack to %player%");
        Skript.registerEffect(EffAddItem.class, "add %itemstack% to [the] (back|mine)pack of %player%", "add %itemstack% to %player%'s (back|mine)pack");
        Skript.registerEffect(EffRemoveItem.class, "(remove|delete) %itemstack% from [the] (back|mine)pack of %player%", "(remove|delete) %itemstack% from %player%'s (back|mine)pack");

        //Expressions
        Skript.registerExpression(ExprBackpackItems.class, ItemStack.class, ExpressionType.COMBINED, "[the] (items in|contents of) [the] (back|mine)pack of %player%", "[the] (items in|contents of) %player%'s (back|mine)pack");
        Skript.registerExpression(ExprBackpackSize.class, Number.class, ExpressionType.COMBINED, "[the] size of [the] (back|mine)pack of %player%", "[the] size of %player%'s (back|mine)pack");
        Skript.registerExpression(ExprSlotItem.class, ItemStack.class, ExpressionType.COMBINED, "[[the ]item in] slot %integer% of [the] (back|mine)pack of %player%", "[[the ]item in] slot %integer% of %player%'s (back|mine)pack");
    }
}
