package us._donut_.skuniversal.bitcoin;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.OfflinePlayer;

public class BitcoinRegister {

    public BitcoinRegister() {
        //Effects
        Skript.registerEffect(EffOpenBitcoinMenu.class, "open [the] bitcoin [main] menu to %player%");
        Skript.registerEffect(EffOpenBitcoinMining.class, "open [the] bitcoin min(e|ing) (menu|interface) to %player%");
        Skript.registerEffect(EffFluctuate.class, "make [the] bitcoin value (fluctuate|change) [randomly]");

        //Expressions
        Skript.registerExpression(ExprBitcoinValue.class, Number.class, ExpressionType.SIMPLE, "[the] [current] bitcoin (value|worth)");
        Skript.registerExpression(ExprExchangeCurrencySymbol.class, String.class, ExpressionType.SIMPLE, "[the] [bitcoin] exchange currency symbol");
        Skript.registerExpression(ExprAmountInBank.class, Number.class, ExpressionType.SIMPLE, "[the] (amount|number) of bitcoins in [the] bank");
        Skript.registerExpression(ExprBitcoinTopPlayers.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] top bitcoin players");
        Skript.registerExpression(ExprAmountInCirculation.class, Number.class, ExpressionType.SIMPLE, "[the] [total] (amount|number) of bitcoins [in circulation]");
        Skript.registerExpression(ExprCirculationLimit.class, Number.class, ExpressionType.SIMPLE, "[the] bitcoin circulation limit");
        Skript.registerExpression(ExprBitcoinBalance.class, Number.class, ExpressionType.COMBINED, "[the] bitcoin balance of %offlineplayer%", "%offlineplayer%'s bitcoin balance");
        Skript.registerExpression(ExprBitcoinsMined.class, Number.class, ExpressionType.SIMPLE, "[the] (amount|number) of bitcoins mined by %offlineplayer%");
        Skript.registerExpression(ExprBitcoinPuzzlesSolved.class, Number.class, ExpressionType.SIMPLE, "[the] (amount|number) of bitcoin [min(e|ing)] puzzles solved by %offlineplayer%");
    }
}