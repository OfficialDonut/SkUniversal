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
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.partiesAPI;

@Name("Parties - Party Description")
@Description("Returns the description of a party.")
@Examples({"send the description of the party named \"cool\""})
public class ExprDescription extends SimpleExpression<String> {
	
	static {
		Skript.registerExpression(ExprDescription.class, String.class, ExpressionType.COMBINED,
				"[the] desc[ription] of [the] party [(named|with name)] %string%",
				"[the] party [(named|with name)] %string%'s desc[ription]");
	}
	
	private Expression<String> name;
	
	@Override
	public boolean isSingle() {
		return true;
	}
	
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
		name = (Expression<String>) e[0];
		return true;
	}
	
	@Override
	public String toString(@Nullable Event e, boolean b) {
		return "description of party named " + name.toString(e, b);
	}
	
	@Override
	@Nullable
	protected String[] get(Event e) {
		if (name.getSingle(e) == null) return null;
		Party party = partiesAPI.getParty(name.getSingle(e));
		if (party != null) {
			return new String[]{party.getDescription()};
		}
		return new String[0];
	}
	
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
		String newDescription = (String) delta[0];
		if (name.getSingle(e) == null) return;
		if (mode == Changer.ChangeMode.SET) {
			Party party = partiesAPI.getParty(name.getSingle(e));
			if (party != null)
				party.setDescription(newDescription);
		}
	}
	
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(String.class) : null;
	}
}