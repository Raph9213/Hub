package net.samagames.hub.games.types;

import net.samagames.api.games.GamesNames;
import net.samagames.api.stats.IPlayerStats;
import net.samagames.hub.Hub;
import net.samagames.hub.games.AbstractGame;
import net.samagames.hub.games.leaderboards.HubLeaderboard;
import net.samagames.hub.games.leaderboards.RotatingLeaderboard;
import net.samagames.hub.games.shops.ShopCategory;
import net.samagames.tools.RulesBook;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/*
 * This file is part of Hub.
 *
 * Hub is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Hub is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Hub.  If not, see <http://www.gnu.org/licenses/>.
 */
public class UHCZoneGame extends AbstractGame
{
    public UHCZoneGame(Hub hub)
    {
        super(hub);
    }

    @Override
    public String getCodeName()
    {
        return "uhczone";
    }

    @Override
    public String getName()
    {
        return "Zone UHC";
    }

    @Override
    public String getCategory()
    {
        return "PvP";
    }

    @Override
    public ItemStack getIcon()
    {
        return new ItemStack(Material.GOLDEN_APPLE, 1);
    }

    @Override
    public String[] getDescription()
    {
        return new String[] {
                "La Zone où tous nos jeux Ultra Hard",
                "Core sont réunis !",
                "",
                "\u2B29 DoubleRunner",
                "\u2B29 Run4Flag"
        };
    }

    @Override
    public String[] getDevelopers()
    {
        return new String[] {
                "IamBlueSlime",
                "Rigner",
                "Thog"
        };
    }

    @Override
    public String getWebsiteDescriptionURL()
    {
        return null;
    }

    @Override
    public int getSlotInMainMenu()
    {
        return 23;
    }

    @Override
    public ShopCategory getShopConfiguration()
    {
        return null;
    }

    @Override
    public Location getLobbySpawn()
    {
        return new Location(this.hub.getWorld(), -58.5D, 108.0D, -3.5D, 90.0F, 0.0F);
    }

    @Override
    public Location getWebsiteDescriptionSkull()
    {
        return null;
    }

    @Override
    public int getOnlinePlayers()
    {
        int players = 0;

        players += this.hub.getGameManager().getGameByIdentifier("uhc").getOnlinePlayers();
        players += this.hub.getGameManager().getGameByIdentifier("uhcrun").getOnlinePlayers();
        players += this.hub.getGameManager().getGameByIdentifier("doublerunner").getOnlinePlayers();
        players += this.hub.getGameManager().getGameByIdentifier("uhcrandom").getOnlinePlayers();
        players += this.hub.getGameManager().getGameByIdentifier("randomrun").getOnlinePlayers();
        players += this.hub.getGameManager().getGameByIdentifier("ultraflagkeeper").getOnlinePlayers();

        return players;
    }

    @Override
    public List<HubLeaderboard> getLeaderBoards()
    {
        List<HubLeaderboard> leaderBoards = new ArrayList<>();

        List<HubLeaderboard.HubLeaderBoardStand> leaderBoardStands1 = new ArrayList<>();
        leaderBoardStands1.add(new HubLeaderboard.HubLeaderBoardStand(new Location(this.hub.getWorld(), -78, 112, 0), new Location(this.hub.getWorld(), -77.5, 114, 1.5)));
        leaderBoardStands1.add(new HubLeaderboard.HubLeaderBoardStand(new Location(this.hub.getWorld(), -76, 111, -1), new Location(this.hub.getWorld(), -75.5, 113, 0.5)));
        leaderBoardStands1.add(new HubLeaderboard.HubLeaderBoardStand(new Location(this.hub.getWorld(), -80, 111, 0), new Location(this.hub.getWorld(), -79.5, 113, 1.5)));

        List<RotatingLeaderboard.RotatingLeaderboardFrame> frames1 = new ArrayList<>();
        frames1.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.UHCRUN, "UHCRun", "Meurtres", "kills"));
        frames1.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.DOUBLERUNNER, "DoubleRunner", "Meurtres", "kills"));
        //frames1.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.UHCORIGINAL, "UHC", "Meurtres", "kills"));
        frames1.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.RANDOMRUN, "RandomRun", "Meurtres", "kills"));
        frames1.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.UHCRANDOM, "UHCRandom", "Meurtres", "kills"));
        frames1.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.ULTRAFLAGKEEPER, "Run4Flag", "Meurtres", "kills"));

        leaderBoards.add(new RotatingLeaderboard(this.hub, new Location(this.hub.getWorld(), -78, 111, 0), leaderBoardStands1, frames1));

        List<HubLeaderboard.HubLeaderBoardStand> leaderBoardStands2 = new ArrayList<>();
        leaderBoardStands2.add(new HubLeaderboard.HubLeaderBoardStand(new Location(this.hub.getWorld(), -84, 112, -1), new Location(this.hub.getWorld(), -83.5, 114, 0.5)));
        leaderBoardStands2.add(new HubLeaderboard.HubLeaderBoardStand(new Location(this.hub.getWorld(), -82, 111, 0), new Location(this.hub.getWorld(), -81.5, 113, 1.5)));
        leaderBoardStands2.add(new HubLeaderboard.HubLeaderBoardStand(new Location(this.hub.getWorld(), -86, 111, -2), new Location(this.hub.getWorld(), -85.5, 113, -0.5)));

        List<RotatingLeaderboard.RotatingLeaderboardFrame> frames2 = new ArrayList<>();
        frames2.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.UHCRUN, "UHCRun", "Victoires", "wins"));
        frames2.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.DOUBLERUNNER, "DoubleRunner", "Victoires", "wins"));
        //frames2.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.UHCORIGINAL, "UHC", "Victoires", "wins"));
        frames2.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.RANDOMRUN, "RandomRun", "Victoires", "wins"));
        frames2.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.UHCRANDOM, "UHCRandom", "Victoires", "wins"));
        frames2.add(new RotatingLeaderboard.RotatingLeaderboardFrame(GamesNames.ULTRAFLAGKEEPER, "Run4Flag", "Victoires", "wins"));

        leaderBoards.add(new RotatingLeaderboard(this.hub, new Location(this.hub.getWorld(), -84, 111, -1), leaderBoardStands2, frames2));

        return leaderBoards;
    }

    @Override
    public State getState()
    {
        return State.POPULAR;
    }

    @Override
    public boolean hasResourcesPack()
    {
        return false;
    }

    @Override
    public boolean isPlayerFirstGame(IPlayerStats playerStats)
    {
        return false;
    }

    @Override
    public boolean isGroup()
    {
        return true;
    }
}
