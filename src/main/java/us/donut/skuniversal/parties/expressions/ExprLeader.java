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
import com.alessiodp.parties.api.interfaces.Party;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Party Leader")
@Description("Returns the leader of a party.")
@Examples({"send \"%the leader of the party named \"\"cool\"\"%\""})
public class ExprLeader extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprLeader.class, OfflinePlayer.class, ExpressionType.COMBINED,
                "[the] leader of [the] party [(named|with name)] %string%",
                "[the] party [(named|with name)] %string%'s leader");
    }

    private Expression<String> name;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "leader of party named " + name.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (name.getSingle(e) == null) return null;
        Party party = partiesAPI.getParty(name.getSingle(e));
        if (party != null) {
            if (party.getLeader() != null)
                return new OfflinePlayer[]{Bukkit.getOfflinePlayer(party.getLeader())};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        OfflinePlayer newLeader = (OfflinePlayer) delta[0];
        if (name.getSingle(e) == null) return;
        if (mode == Changer.ChangeMode.SET) {
            Party party = partiesAPI.getParty(name.getSingle(e));
            if (party != null) {
                PartyPlayer partyPlayer = partiesAPI.getPartyPlayer(newLeader.getUniqueId());
                if (partyPlayer != null)
                    party.changeLeader(partyPlayer);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(OfflinePlayer.class) : null;
    }
}