package us.donut.skuniversal.parties;

import com.alessiodp.parties.Parties;
import com.alessiodp.parties.handlers.PartyHandler;
import com.alessiodp.parties.handlers.PlayerHandler;
import com.alessiodp.parties.utils.api.PartiesAPI;

public class PartiesHook {

    public static PartiesAPI partiesAPI = new PartiesAPI();
    public static PartyHandler partyHandler = Parties.getInstance().getPartyHandler();
    public static PlayerHandler playerHandler = Parties.getInstance().getPlayerHandler();

}
