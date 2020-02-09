package us.donut.skuniversal.parties.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Party Kills")
@Description("Returns the amount of kills of a party.")
@Examples({"send \"%the kills of the party named \"cool\"%\""})
public class ExprPartyKills extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprPartyKills.class, Number.class, ExpressionType.COMBINED,
                "[the] [(number|amount) of] kills of [the] party [(named|with name)] %string%",
                "[the] party [(named|with name)] %string%'s [(number|amount) of] kills");

    }

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "kills of party named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (name.getSingle(e) == null) return null;
        return new Number[]{partiesAPI.getParty(name.getSingle(e)).getKills()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (name.getSingle(e) == null) return;
        int killsChange = ((Number) delta[0]).intValue();
        Number currentKills = partiesAPI.getParty(name.getSingle(e)).getKills();
        if (mode == Changer.ChangeMode.SET) {
            partiesAPI.getParty(name.getSingle(e)).setKills(killsChange);
        } else if (mode == Changer.ChangeMode.ADD) {
            partiesAPI.getParty(name.getSingle(e)).setKills(currentKills.intValue() + killsChange);
        } else if (mode == Changer.ChangeMode.REMOVE) {
           partiesAPI.getParty(name.getSingle(e)).setKills(currentKills.intValue() - killsChange);
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(Number.class) : null;
    }
}