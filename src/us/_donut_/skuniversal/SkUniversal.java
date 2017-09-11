package us._donut_.skuniversal;

import ch.njol.skript.Skript;
import org.bukkit.plugin.java.JavaPlugin;
import us._donut_.skuniversal.advancedban.AdvancedBanRegister;
import us._donut_.skuniversal.advancedsurvivalgames.AdvancedSurvivalGamesRegister;
import us._donut_.skuniversal.autorank.AutorankRegister;
import us._donut_.skuniversal.bedwars.BedwarsRegister;
import us._donut_.skuniversal.clearlagg.ClearLagRegister;
import us._donut_.skuniversal.combatlog.CombatLogRegister;
import us._donut_.skuniversal.lockette.LocketteRegister;
import us._donut_.skuniversal.lockettepro.LocketteProRegister;
import us._donut_.skuniversal.lwc.LWCRegister;
import us._donut_.skuniversal.minepacks.MinePacksRegister;
import us._donut_.skuniversal.parties.PartiesRegister;
import us._donut_.skuniversal.pvplevels.PvPLevelsRegister;
import us._donut_.skuniversal.shopkeepers.ShopkeepersRegister;
import us._donut_.skuniversal.skywars_cookloco.SkywarsCookLocoRegister;
import us._donut_.skuniversal.skywars_daboross.SkyWarsDaborossRegister;

public class SkUniversal extends JavaPlugin {
    @Override
    public void onEnable() {
        Skript.registerAddon(this);
        AdvancedBanRegister.registerAdvancedBan();
        AdvancedSurvivalGamesRegister.registerAdvancedSurvivalGames();
        AutorankRegister.registerAutorank();
        BedwarsRegister.registerBedwars();
        ClearLagRegister.registerClearLagg();
        CombatLogRegister.registerCombatLog();
        LocketteRegister.registerLockette();
        LocketteProRegister.registerLockettePro();
        LWCRegister.registerLWC();
        MinePacksRegister.registerMinePacks();
        PartiesRegister.registerParties();
        PvPLevelsRegister.registerPvPLevels();
        ShopkeepersRegister.registerShopekeepers();
        SkywarsCookLocoRegister.registerSkyWarsCookLoco();
        SkyWarsDaborossRegister.registerSkyWarsDaboross();
        getLogger().info("Enabled!");
    }
    @Override
    public void onDisable() {
    }
}