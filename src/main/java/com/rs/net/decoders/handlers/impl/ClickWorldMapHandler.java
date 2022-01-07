// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//  Copyright © 2021 Trenton Kress
//  This file is part of project: Darkan
//
package com.rs.net.decoders.handlers.impl;

import com.rs.game.player.Player;
import com.rs.lib.net.packets.PacketHandler;
import com.rs.lib.net.packets.decoders.WorldMapClick;

public class ClickWorldMapHandler implements PacketHandler<Player, WorldMapClick> {

	@Override
	public void handle(Player player, WorldMapClick packet) {
		int hash = player.getTempAttribs().getI("worldHash");
		if (packet.getTile().getTileHash() != hash)
			player.getTempAttribs().setI("worldHash", packet.getTile().getTileHash());
		else {
			player.getHintIconsManager().removeAll();
			player.getTempAttribs().removeI("worldHash");
			player.getHintIconsManager().addHintIcon(packet.getTile().getX(), packet.getTile().getY(), packet.getTile().getPlane(), 20, 0, 2, -1, true);
			player.getVars().setVar(1159, packet.getTile().getTileHash());
		}
	}

}
