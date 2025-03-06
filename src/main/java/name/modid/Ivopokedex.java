package name.modid;

import net.fabricmc.api.ModInitializer;
import java.util.stream.IntStream;

import net.minecraft.server.MinecraftServer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokedex.PokedexEntryProgress;
import com.cobblemon.mod.common.api.pokedex.PokedexManager;
import com.cobblemon.mod.common.api.pokedex.SpeciesDexRecord;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ivopokedex implements ModInitializer {
	public static final String MOD_ID = "ivopokedex";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static int[] faltaLista= {};
	public static final String[] pokedexList= {
			"pacientecero",
			"Bulbasaur",
			"Ivysaur",
			"Venusaur",
			"Charmander",
			"Charmeleon",
			"Charizard",
			"Squirtle",
			"Wartortle",
			"Blastoise",
			"Caterpie",
			"Metapod",
			"Butterfree",
			"Weedle",
			"Kakuna",
			"Beedrill",
			"Pidgey",
			"Pidgeotto",
			"Pidgeot",
			"Rattata",
			"Raticate",
			"Spearow",
			"Fearow",
			"Ekans",
			"Arbok",
			"Pikachu",
			"Raichu",
			"Sandshrew",
			"Sandslash",
			"Nidoranm",
			"Nidorina",
			"Nidoqueen",
			"Nidoranf",
			"Nidorino",
			"Nidoking",
			"Clefairy",
			"Clefable",
			"Vulpix",
			"Ninetales",
			"Jigglypuff",
			"Wigglytuff",
			"Zubat",
			"Golbat",
			"Oddish",
			"Gloom",
			"Vileplume",
			"Paras",
			"Parasect",
			"Venonat",
			"Venomoth",
			"Diglett",
			"Dugtrio",
			"Meowth",
			"Persian",
			"Psyduck",
			"Golduck",
			"Mankey",
			"Primeape",
			"Growlithe",
			"Arcanine",
			"Poliwag",
			"Poliwhirl",
			"Poliwrath",
			"Abra",
			"Kadabra",
			"Alakazam",
			"Machop",
			"Machoke",
			"Machamp",
			"Bellsprout",
			"Weepinbell",
			"Victreebel",
			"Tentacool",
			"Tentacruel",
			"Geodude",
			"Graveler",
			"Golem",
			"Ponyta",
			"Rapidash",
			"Slowpoke",
			"Slowbro",
			"Magnemite",
			"Magneton",
			"Farfetchd",
			"Doduo",
			"Dodrio",
			"Seel",
			"Dewgong",
			"Grimer",
			"Muk",
			"Shellder",
			"Cloyster",
			"Gastly",
			"Haunter",
			"Gengar",
			"Onix",
			"Drowzee",
			"Hypno",
			"Krabby",
			"Kingler",
			"Voltorb",
			"Electrode",
			"Exeggcute",
			"Exeggutor",
			"Cubone",
			"Marowak",
			"Hitmonlee",
			"Hitmonchan",
			"Lickitung",
			"Koffing",
			"Weezing",
			"Rhyhorn",
			"Rhydon",
			"Chansey",
			"Tangela",
			"Kangaskhan",
			"Horsea",
			"Seadra",
			"Goldeen",
			"Seaking",
			"Staryu",
			"Starmie",
			"MrMime",
			"Scyther",
			"Jynx",
			"Electabuzz",
			"Magmar",
			"Pinsir",
			"Tauros",
			"Magikarp",
			"Gyarados",
			"Lapras",
			"Ditto",
			"Eevee",
			"Vaporeon",
			"Jolteon",
			"Flareon",
			"Porygon",
			"Omanyte",
			"Omastar",
			"Kabuto",
			"Kabutops",
			"Aerodactyl",
			"Snorlax",
			"Articuno",
			"Zapdos",
			"Moltres",
			"Dratini",
			"Dragonair",
			"Dragonite",
			"Mewtwo",
			"Mew",
			"Chikorita",
			"Bayleef",
			"Meganium",
			"Cyndaquil",
			"Quilava",
			"Typhlosion",
			"Totodile",
			"Croconaw",
			"Feraligatr",
			"Sentret",
			"Furret",
			"Hoothoot",
			"Noctowl",
			"Ledyba",
			"Ledian",
			"Spinarak",
			"Ariados",
			"Crobat",
			"Chinchou",
			"Lanturn",
			"Pichu",
			"Cleffa",
			"Igglybuff",
			"Togepi",
			"Togetic",
			"Natu",
			"Xatu",
			"Mareep",
			"Flaaffy",
			"Ampharos",
			"Bellossom",
			"Marill",
			"Azumarill",
			"Sudowoodo",
			"Politoed",
			"Hoppip",
			"Skiploom",
			"Jumpluff",
			"Aipom",
			"Sunkern",
			"Sunflora",
			"Yanma",
			"Wooper",
			"Quagsire",
			"Espeon",
			"Umbreon",
			"Murkrow",
			"Slowking",
			"Misdreavus",
			"Unown",
			"Wobbuffet",
			"Girafarig",
			"Pineco",
			"Forretress",
			"Dunsparce",
			"Gligar",
			"Steelix",
			"Snubbull",
			"Granbull",
			"Qwilfish",
			"Scizor",
			"Shuckle",
			"Heracross",
			"Sneasel",
			"Teddiursa",
			"Ursaring",
			"Slugma",
			"Magcargo",
			"Swinub",
			"Piloswine",
			"Corsola",
			"Remoraid",
			"Octillery",
			"Delibird",
			"Mantine",
			"Skarmory",
			"Houndour",
			"Houndoom",
			"Kingdra",
			"Phanpy",
			"Donphan",
			"Porygon2",
			"Stantler",
			"Smeargle",
			"Tyrogue",
			"Hitmontop",
			"Smoochum",
			"Elekid",
			"Magby",
			"Miltank",
			"Blissey",
			"Raikou",
			"Entei",
			"Suicune",
			"Larvitar",
			"Pupitar",
			"Tyranitar",
			"Lugia",
			"HoOh",
			"Celebi",
			"Treecko",
			"Grovyle",
			"Sceptile",
			"Torchic",
			"Combusken",
			"Blaziken",
			"Mudkip",
			"Marshtomp",
			"Swampert",
			"Poochyena",
			"Mightyena",
			"Zigzagoon",
			"Linoone",
			"Wurmple",
			"Silcoon",
			"Beautifly",
			"Cascoon",
			"Dustox",
			"Lotad",
			"Lombre",
			"Ludicolo",
			"Seedot",
			"Nuzleaf",
			"Shiftry",
			"Taillow",
			"Swellow",
			"Wingull",
			"Pelipper",
			"Ralts",
			"Kirlia",
			"Gardevoir",
			"Surskit",
			"Masquerain",
			"Shroomish",
			"Breloom",
			"Slakoth",
			"Vigoroth",
			"Slaking",
			"Nincada",
			"Ninjask",
			"Shedinja",
			"Whismur",
			"Loudred",
			"Exploud",
			"Makuhita",
			"Hariyama",
			"Azurill",
			"Nosepass",
			"Skitty",
			"Delcatty",
			"Sableye",
			"Mawile",
			"Aron",
			"Lairon",
			"Aggron",
			"Meditite",
			"Medicham",
			"Electrike",
			"Manectric",
			"Plusle",
			"Minun",
			"Volbeat",
			"Illumise",
			"Roselia",
			"Gulpin",
			"Swalot",
			"Carvanha",
			"Sharpedo",
			"Wailmer",
			"Wailord",
			"Numel",
			"Camerupt",
			"Torkoal",
			"Spoink",
			"Grumpig",
			"Spinda",
			"Trapinch",
			"Vibrava",
			"Flygon",
			"Cacnea",
			"Cacturne",
			"Swablu",
			"Altaria",
			"Zangoose",
			"Seviper",
			"Lunatone",
			"Solrock",
			"Barboach",
			"Whiscash",
			"Corphish",
			"Crawdaunt",
			"Baltoy",
			"Claydol",
			"Lileep",
			"Cradily",
			"Anorith",
			"Armaldo",
			"Feebas",
			"Milotic",
			"Castform",
			"Kecleon",
			"Shuppet",
			"Banette",
			"Duskull",
			"Dusclops",
			"Tropius",
			"Chimecho",
			"Absol",
			"Wynaut",
			"Snorunt",
			"Glalie",
			"Spheal",
			"Sealeo",
			"Walrein",
			"Clamperl",
			"Huntail",
			"Gorebyss",
			"Relicanth",
			"Luvdisc",
			"Bagon",
			"Shelgon",
			"Salamence",
			"Beldum",
			"Metang",
			"Metagross",
			"Regirock",
			"Regice",
			"Registeel",
			"Latias",
			"Latios",
			"Kyogre",
			"Groudon",
			"Rayquaza",
			"Jirachi",
			"Deoxys",
			"Turtwig",
			"Grotle",
			"Torterra",
			"Chimchar",
			"Monferno",
			"Infernape",
			"Piplup",
			"Prinplup",
			"Empoleon",
			"Starly",
			"Staravia",
			"Staraptor",
			"Bidoof",
			"Bibarel",
			"Kricketot",
			"Kricketune",
			"Shinx",
			"Luxio",
			"Luxray",
			"Budew",
			"Roserade",
			"Cranidos",
			"Rampardos",
			"Shieldon",
			"Bastiodon",
			"Burmy",
			"Wormadam",
			"Mothim",
			"Combee",
			"Vespiquen",
			"Pachirisu",
			"Buizel",
			"Floatzel",
			"Cherubi",
			"Cherrim",
			"Shellos",
			"Gastrodon",
			"Ambipom",
			"Drifloon",
			"Drifblim",
			"Buneary",
			"Lopunny",
			"Mismagius",
			"Honchkrow",
			"Glameow",
			"Purugly",
			"Chingling",
			"Stunky",
			"Skuntank",
			"Bronzor",
			"Bronzong",
			"Bonsly",
			"MimeJr",
			"Happiny",
			"Chatot",
			"Spiritomb",
			"Gible",
			"Gabite",
			"Garchomp",
			"Munchlax",
			"Riolu",
			"Lucario",
			"Hippopotas",
			"Hippowdon",
			"Skorupi",
			"Drapion",
			"Croagunk",
			"Toxicroak",
			"Carnivine",
			"Finneon",
			"Lumineon",
			"Mantyke",
			"Snover",
			"Abomasnow",
			"Weavile",
			"Magnezone",
			"Lickilicky",
			"Rhyperior",
			"Tangrowth",
			"Electivire",
			"Magmortar",
			"Togekiss",
			"Yanmega",
			"Leafeon",
			"Glaceon",
			"Gliscor",
			"Mamoswine",
			"PorygonZ",
			"Gallade",
			"Probopass",
			"Dusknoir",
			"Froslass",
			"Rotom",
			"Uxie",
			"Mesprit",
			"Azelf",
			"Dialga",
			"Palkia",
			"Heatran",
			"Regigigas",
			"Giratina",
			"Cresselia",
			"Phione",
			"Manaphy",
			"Darkrai",
			"Shaymin",
			"Arceus",
			"Victini",
			"Snivy",
			"Servine",
			"Serperior",
			"Tepig",
			"Pignite",
			"Emboar",
			"Oshawott",
			"Dewott",
			"Samurott",
			"Patrat",
			"Watchog",
			"Lillipup",
			"Herdier",
			"Stoutland",
			"Purrloin",
			"Liepard",
			"Pansage",
			"Simisage",
			"Pansear",
			"Simisear",
			"Panpour",
			"Simipour",
			"Munna",
			"Musharna",
			"Pidove",
			"Tranquill",
			"Unfezant",
			"Blitzle",
			"Zebstrika",
			"Roggenrola",
			"Boldore",
			"Gigalith",
			"Woobat",
			"Swoobat",
			"Drilbur",
			"Excadrill",
			"Audino",
			"Timburr",
			"Gurdurr",
			"Conkeldurr",
			"Tympole",
			"Palpitoad",
			"Seismitoad",
			"Throh",
			"Sawk",
			"Sewaddle",
			"Swadloon",
			"Leavanny",
			"Venipede",
			"Whirlipede",
			"Scolipede",
			"Cottonee",
			"Whimsicott",
			"Petilil",
			"Lilligant",
			"Basculin",
			"Sandile",
			"Krokorok",
			"Krookodile",
			"Darumaka",
			"Darmanitan",
			"Maractus",
			"Dwebble",
			"Crustle",
			"Scraggy",
			"Scrafty",
			"Sigilyph",
			"Yamask",
			"Cofagrigus",
			"Tirtouga",
			"Carracosta",
			"Archen",
			"Archeops",
			"Trubbish",
			"Garbodor",
			"Zorua",
			"Zoroark",
			"Minccino",
			"Cinccino",
			"Gothita",
			"Gothorita",
			"Gothitelle",
			"Solosis",
			"Duosion",
			"Reuniclus",
			"Ducklett",
			"Swanna",
			"Vanillite",
			"Vanillish",
			"Vanilluxe",
			"Deerling",
			"Sawsbuck",
			"Emolga",
			"Karrablast",
			"Escavalier",
			"Foongus",
			"Amoonguss",
			"Frillish",
			"Jellicent",
			"Alomomola",
			"Joltik",
			"Galvantula",
			"Ferroseed",
			"Ferrothorn",
			"Klink",
			"Klang",
			"Klinklang",
			"Tynamo",
			"Eelektrik",
			"Eelektross",
			"Elgyem",
			"Beheeyem",
			"Litwick",
			"Lampent",
			"Chandelure",
			"Axew",
			"Fraxure",
			"Haxorus",
			"Cubchoo",
			"Beartic",
			"Cryogonal",
			"Shelmet",
			"Accelgor",
			"Stunfisk",
			"Mienfoo",
			"Mienshao",
			"Druddigon",
			"Golett",
			"Golurk",
			"Pawniard",
			"Bisharp",
			"Bouffalant",
			"Rufflet",
			"Braviary",
			"Vullaby",
			"Mandibuzz",
			"Heatmor",
			"Durant",
			"Deino",
			"Zweilous",
			"Hydreigon",
			"Larvesta",
			"Volcarona",
			"Cobalion",
			"Terrakion",
			"Virizion",
			"Tornadus",
			"Thundurus",
			"Reshiram",
			"Zekrom",
			"Landorus",
			"Kyurem",
			"Keldeo",
			"Meloetta",
			"Genesect",
			"Chespin",
			"Quilladin",
			"Chesnaught",
			"Fennekin",
			"Braixen",
			"Delphox",
			"Froakie",
			"Frogadier",
			"Greninja",
			"Bunnelby",
			"Diggersby",
			"Fletchling",
			"Fletchinder",
			"Talonflame",
			"Scatterbug",
			"Spewpa",
			"Vivillon",
			"Litleo",
			"Pyroar",
			"Flabebe",
			"Floette",
			"Florges",
			"Skiddo",
			"Gogoat",
			"Pancham",
			"Pangoro",
			"Furfrou",
			"Espurr",
			"Meowstic",
			"Honedge",
			"Doublade",
			"Aegislash",
			"Spritzee",
			"Aromatisse",
			"Swirlix",
			"Slurpuff",
			"Inkay",
			"Malamar",
			"Binacle",
			"Barbaracle",
			"Skrelp",
			"Dragalge",
			"Clauncher",
			"Clawitzer",
			"Helioptile",
			"Heliolisk",
			"Tyrunt",
			"Tyrantrum",
			"Amaura",
			"Aurorus",
			"Sylveon",
			"Hawlucha",
			"Dedenne",
			"Carbink",
			"Goomy",
			"Sliggoo",
			"Goodra",
			"Klefki",
			"Phantump",
			"Trevenant",
			"Pumpkaboo",
			"Gourgeist",
			"Bergmite",
			"Avalugg",
			"Noibat",
			"Noivern",
			"Xerneas",
			"Yveltal",
			"Zygarde",
			"Diancie",
			"Hoopa",
			"Volcanion",
			"Rowlet",
			"Dartrix",
			"Decidueye",
			"Litten",
			"Torracat",
			"Incineroar",
			"Popplio",
			"Brionne",
			"Primarina",
			"Pikipek",
			"Trumbeak",
			"Toucannon",
			"Yungoos",
			"Gumshoos",
			"Grubbin",
			"Charjabug",
			"Vikavolt",
			"Crabrawler",
			"Crabominable",
			"Oricorio",
			"Cutiefly",
			"Ribombee",
			"Rockruff",
			"Lycanroc",
			"Wishiwashi",
			"Mareanie",
			"Toxapex",
			"Mudbray",
			"Mudsdale",
			"Dewpider",
			"Araquanid",
			"Fomantis",
			"Lurantis",
			"Morelull",
			"Shiinotic",
			"Salandit",
			"Salazzle",
			"Stufful",
			"Bewear",
			"Bounsweet",
			"Steenee",
			"Tsareena",
			"Comfey",
			"Oranguru",
			"Passimian",
			"Wimpod",
			"Golisopod",
			"Sandygast",
			"Palossand",
			"Pyukumuku",
			"TypeNull",
			"Silvally",
			"Minior",
			"Komala",
			"Turtonator",
			"Togedemaru",
			"Mimikyu",
			"Bruxish",
			"Drampa",
			"Dhelmise",
			"Jangmoo",
			"Hakamoo",
			"Kommoo",
			"TapuKoko",
			"TapuLele",
			"TapuBulu",
			"TapuFini",
			"Cosmog",
			"Cosmoem",
			"Solgaleo",
			"Lunala",
			"Nihilego",
			"Buzzwole",
			"Pheromosa",
			"Xurkitree",
			"Celesteela",
			"Kartana",
			"Guzzlord",
			"Necrozma",
			"Magearna",
			"Marshadow",
			"Poipole",
			"Naganadel",
			"Stakataka",
			"Blacephalon",
			"Zeraora",
			"Meltan",
			"Melmetal",
			"Grookey",
			"Thwackey",
			"Rillaboom",
			"Scorbunny",
			"Raboot",
			"Cinderace",
			"Sobble",
			"Drizzile",
			"Inteleon",
			"Skwovet",
			"Greedent",
			"Rookidee",
			"Corvisquire",
			"Corviknight",
			"Blipbug",
			"Dottler",
			"Orbeetle",
			"Nickit",
			"Thievul",
			"Gossifleur",
			"Eldegoss",
			"Wooloo",
			"Dubwool",
			"Chewtle",
			"Drednaw",
			"Yamper",
			"Boltund",
			"Rolycoly",
			"Carkol",
			"Coalossal",
			"Applin",
			"Flapple",
			"Appletun",
			"Silicobra",
			"Sandaconda",
			"Cramorant",
			"Arrokuda",
			"Barraskewda",
			"Toxel",
			"Toxtricity",
			"Sizzlipede",
			"Centiskorch",
			"Clobbopus",
			"Grapploct",
			"Sinistea",
			"Polteageist",
			"Hatenna",
			"Hattrem",
			"Hatterene",
			"Impidimp",
			"Morgrem",
			"Grimmsnarl",
			"Obstagoon",
			"Perrserker",
			"Cursola",
			"Sirfetchd",
			"MrRime",
			"Runerigus",
			"Milcery",
			"Alcremie",
			"Falinks",
			"Pincurchin",
			"Snom",
			"Frosmoth",
			"Stonjourner",
			"Eiscue",
			"Indeedee",
			"Morpeko",
			"Cufant",
			"Copperajah",
			"Dracozolt",
			"Arctozolt",
			"Dracovish",
			"Arctovish",
			"Duraludon",
			"Dreepy",
			"Drakloak",
			"Dragapult",
			"Zacian",
			"Zamazenta",
			"Eternatus",
			"Kubfu",
			"Urshifu",
			"Zarude",
			"Regieleki",
			"Regidrago",
			"Glastrier",
			"Spectrier",
			"Calyrex",
			"Wyrdeer",
			"Kleavor",
			"Ursaluna",
			"Basculegion",
			"Sneasler",
			"Overqwil",
			"Enamorus",
			"Sprigatito",
			"Floragato",
			"Meowscarada",
			"Fuecoco",
			"Crocalor",
			"Skeledirge",
			"Quaxly",
			"Quaxwell",
			"Quaquaval",
			"Lechonk",
			"Oinkologne",
			"Tarountula",
			"Spidops",
			"Nymble",
			"Lokix",
			"Pawmi",
			"Pawmo",
			"Pawmot",
			"Tandemaus",
			"Maushold",
			"Fidough",
			"Dachsbun",
			"Smoliv",
			"Dolliv",
			"Arboliva",
			"Squawkabilly",
			"Nacli",
			"Naclstack",
			"Garganacl",
			"Charcadet",
			"Armarouge",
			"Ceruledge",
			"Tadbulb",
			"Bellibolt",
			"Wattrel",
			"Kilowattrel",
			"Maschiff",
			"Mabosstiff",
			"Shroodle",
			"Grafaiai",
			"Bramblin",
			"Brambleghast",
			"Toedscool",
			"Toedscruel",
			"Klawf",
			"Capsakid",
			"Scovillain",
			"Rellor",
			"Rabsca",
			"Flittle",
			"Espathra",
			"Tinkatink",
			"Tinkatuff",
			"Tinkaton",
			"Wiglett",
			"Wugtrio",
			"Bombirdier",
			"Finizen",
			"Palafin",
			"Varoom",
			"Revavroom",
			"Cyclizar",
			"Orthworm",
			"Glimmet",
			"Glimmora",
			"Greavard",
			"Houndstone",
			"Flamigo",
			"Cetoddle",
			"Cetitan",
			"Veluza",
			"Dondozo",
			"Tatsugiri",
			"Annihilape",
			"Clodsire",
			"Farigiraf",
			"Dudunsparce",
			"Kingambit",
			"GreatTusk",
			"ScreamTail",
			"BruteBonnet",
			"FlutterMane",
			"SlitherWing",
			"SandyShocks",
			"IronTreads",
			"IronBundle",
			"IronHands",
			"IronJugulis",
			"IronMoth",
			"IronThorns",
			"Frigibax",
			"Arctibax",
			"Baxcalibur",
			"Gimmighoul",
			"Gholdengo",
			"WoChien",
			"ChienPao",
			"TingLu",
			"ChiYu",
			"RoaringMoon",
			"IronValiant",
			"Koraidon",
			"Miraidon",
			"WalkingWake",
			"IronLeaves",
			"Dipplin",
			"Poltchageist",
			"Sinistcha",
			"Okidogi",
			"Munkidori",
			"Fezandipiti",
			"Ogerpon",
			"Archaludon",
			"Hydrapple",
			"GougingFire",
			"RagingBolt",
			"IronBoulder",
			"IronCrown",
			"Terapagos",
			"Pecharunt"
	};
	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
		    dispatcher.register(literal("ivopokedex")
		        // Subcomando "intervalo" con dos argumentos numéricos
		        .then(literal("intervalo")
		            .then(argument("numero1", IntegerArgumentType.integer())
		                .then(argument("numero2", IntegerArgumentType.integer())
		                    // Ejecución sin "visto"
		                    .executes(context -> {
		                        int numero1 = IntegerArgumentType.getInteger(context, "numero1");
		                        int numero2 = IntegerArgumentType.getInteger(context, "numero2");
		                        return ejecutarIntervalo(context, numero1, numero2, false);
		                    })
		                    // Rama opcional con "visto"
		                    .then(literal("visto")
		                        .executes(context -> {
		                            int numero1 = IntegerArgumentType.getInteger(context, "numero1");
		                            int numero2 = IntegerArgumentType.getInteger(context, "numero2");
		                            return ejecutarIntervalo(context, numero1, numero2, true);
		                        })
		                    )
		                )
		            )
		        )
		        // Subcomando "especie" (ruta "nombre")
		        .then(literal("especie")
		            .then(literal("nombre")
		                .then(argument("nombre", StringArgumentType.string())
		                    // Ejecución sin "visto"
		                    .executes(context -> {
		                        String nombre = StringArgumentType.getString(context, "nombre");
		                        return ejecutarEspecie(context, indexOfContains(pokedexList, nombre), false);
		                    })
		                    // Rama opcional con "visto"
		                    .then(literal("visto")
		                        .executes(context -> {
		                            String nombre = StringArgumentType.getString(context, "nombre");
		                            return ejecutarEspecie(context, indexOfContains(pokedexList, nombre), true);
		                        })
		                    )
		                )
		            )
		        )
		        // Subcomando "especie" (ruta "numero")
		        .then(literal("especie")
		            .then(literal("numero")
		                .then(argument("numero", IntegerArgumentType.integer())
		                    // Ejecución sin "visto"
		                    .executes(context -> {
		                        int numero = IntegerArgumentType.getInteger(context, "numero");
		                        return ejecutarEspecie(context, numero, false);
		                    })
		                    // Rama opcional con "visto"
		                    .then(literal("visto")
		                        .executes(context -> {
		                            int numero = IntegerArgumentType.getInteger(context, "numero");
		                            return ejecutarEspecie(context, numero, true);
		                        })
		                    )
		                )
		            )
		        )
		    );
		});
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                literal("ivoparty")
                .then(literal("party")
	                .then(
	                    // Argumento obligatorio
	                    argument("pokemon1", StringArgumentType.word())
	                    .executes(ctx -> {
	                        String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                        // Si solo se proporciona el argumento obligatorio
	                        return checkParty(-1, pokemon1, "", "", "", "", "", ctx);
	                    })
	                    .then(
	                        argument("pokemon2", StringArgumentType.word())
	                        .executes(ctx -> {
	                            String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                            String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                            return checkParty(-1, pokemon1, pokemon2, "", "", "", "", ctx);
	                        })
	                        .then(
	                            argument("pokemon3", StringArgumentType.word())
	                            .executes(ctx -> {
	                                String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                String pokemon3 = StringArgumentType.getString(ctx, "pokemon3");
	                                return checkParty(-1, pokemon1, pokemon2, pokemon3, "", "", "", ctx);
	                            })
	                            .then(
	                                argument("pokemon4", StringArgumentType.word())
	                                .executes(ctx -> {
	                                    String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                    String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                    String pokemon3 = StringArgumentType.getString(ctx, "pokemon3");
	                                    String pokemon4 = StringArgumentType.getString(ctx, "pokemon4");
	                                    return checkParty(-1, pokemon1, pokemon2, pokemon3, pokemon4, "", "", ctx);
	                                })
	                                .then(
	                                    argument("pokemon5", StringArgumentType.word())
	                                    .executes(ctx -> {
	                                        String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                        String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                        String pokemon3 = StringArgumentType.getString(ctx, "pokemon3");
	                                        String pokemon4 = StringArgumentType.getString(ctx, "pokemon4");
	                                        String pokemon5 = StringArgumentType.getString(ctx, "pokemon5");
	                                        return checkParty(-1, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, "", ctx);
	                                    })
	                                    .then(
	                                        argument("pokemon6", StringArgumentType.word())
	                                        .executes(ctx -> {
	                                            String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                            String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                            String pokemon3 = StringArgumentType.getString(ctx, "pokemon3");
	                                            String pokemon4 = StringArgumentType.getString(ctx, "pokemon4");
	                                            String pokemon5 = StringArgumentType.getString(ctx, "pokemon5");
	                                            String pokemon6 = StringArgumentType.getString(ctx, "pokemon6");
	                                            return checkParty(-1, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, ctx);
	                                        })
	                                    )
	                                )
	                            )
	                        )
	                    )
	                )
                )
            );
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                literal("ivoparty")
                .then(literal("slot")
	                .then(
	                    // Nuevo argumento entero "slot"
	                    argument("slot", IntegerArgumentType.integer())
	                    .then(
	                        // Argumento obligatorio de pokemon1
	                        argument("pokemon1", StringArgumentType.word())
	                        .executes(ctx -> {
	                            int slot = IntegerArgumentType.getInteger(ctx, "slot");
	                            String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                            // Si solo se proporciona el argumento obligatorio, se llama a checkParty con el slot y el pokemon1
	                            return checkParty(slot-1, pokemon1, "", "", "", "", "", ctx);
	                        })
	                        .then(
	                            argument("pokemon2", StringArgumentType.word())
	                            .executes(ctx -> {
	                                int slot = IntegerArgumentType.getInteger(ctx, "slot");
	                                String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                return checkParty(slot-1, pokemon1, pokemon2, "", "", "", "", ctx);
	                            })
	                            .then(
	                                argument("pokemon3", StringArgumentType.word())
	                                .executes(ctx -> {
	                                    int slot = IntegerArgumentType.getInteger(ctx, "slot");
	                                    String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                    String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                    String pokemon3 = StringArgumentType.getString(ctx, "pokemon3");
	                                    return checkParty(slot-1, pokemon1, pokemon2, pokemon3, "", "", "", ctx);
	                                })
	                                .then(
	                                    argument("pokemon4", StringArgumentType.word())
	                                    .executes(ctx -> {
	                                        int slot = IntegerArgumentType.getInteger(ctx, "slot");
	                                        String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                        String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                        String pokemon3 = StringArgumentType.getString(ctx, "pokemon3");
	                                        String pokemon4 = StringArgumentType.getString(ctx, "pokemon4");
	                                        return checkParty(slot-1, pokemon1, pokemon2, pokemon3, pokemon4, "", "", ctx);
	                                    })
	                                    .then(
	                                        argument("pokemon5", StringArgumentType.word())
	                                        .executes(ctx -> {
	                                            int slot = IntegerArgumentType.getInteger(ctx, "slot");
	                                            String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                            String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                            String pokemon3 = StringArgumentType.getString(ctx, "pokemon3");
	                                            String pokemon4 = StringArgumentType.getString(ctx, "pokemon4");
	                                            String pokemon5 = StringArgumentType.getString(ctx, "pokemon5");
	                                            return checkParty(slot-1, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, "", ctx);
	                                        })
	                                        .then(
	                                            argument("pokemon6", StringArgumentType.word())
	                                            .executes(ctx -> {
	                                                int slot = IntegerArgumentType.getInteger(ctx, "slot");
	                                                String pokemon1 = StringArgumentType.getString(ctx, "pokemon1");
	                                                String pokemon2 = StringArgumentType.getString(ctx, "pokemon2");
	                                                String pokemon3 = StringArgumentType.getString(ctx, "pokemon3");
	                                                String pokemon4 = StringArgumentType.getString(ctx, "pokemon4");
	                                                String pokemon5 = StringArgumentType.getString(ctx, "pokemon5");
	                                                String pokemon6 = StringArgumentType.getString(ctx, "pokemon6");
	                                                return checkParty(slot-1, pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6, ctx);
	                                            })
	                                        )
	                                    )
	                                )
	                            )
	                        )
	                    )
	                )
	            )
            );
        });

    }
	public static int ejecutarEspecie(CommandContext<ServerCommandSource> context,int numero,boolean visto) {
		return ejecutarIntervalo(context,numero,numero,visto);
	}

	public static int ejecutarIntervalo(CommandContext<ServerCommandSource> context,int numero1,int numero2,boolean visto) {
	    ServerCommandSource source = context.getSource();
	    ServerPlayerEntity player = source.getPlayer();
	    int c=0;
	    for(int i=numero1;i<=numero2;i++) {
	    	boolean tag=false;
	    	for(int j=0;j<faltaLista.length;j++) {
	    		if(faltaLista[j]==i) {
	    			c++;
	    			tag=true;
	    		}
	    	}
	    	PokedexManager pokedexData=Cobblemon.INSTANCE.getPlayerDataManager().getPokedexData(player);
	    	if (!tag) {
	    		Identifier expectedId = Identifier.of("cobblemon", pokedexList[i].toLowerCase());

	    		if (pokedexData.getSpeciesRecords().containsKey(expectedId)) {
	    			SpeciesDexRecord registro = pokedexData.getSpeciesRecords().get(expectedId);
	    			PokedexEntryProgress progreso = registro.getKnowledge();
	    			// Si no se está en la rama "visto", solo se cuenta si se ha alcanzado el estado CAUGHT
	    			if (!visto) {
	    				if (progreso.compareTo(PokedexEntryProgress.CAUGHT) >= 0) {
	    					c++;
	    				}
	    			} else {
	    				if (progreso.compareTo(PokedexEntryProgress.ENCOUNTERED) >= 0) {
	    					c++;
	    				}
	    			}
	    		}
	    	}
	    }
	    if(c<(numero2-numero1+1)) {
	    	return 0;
	    }
	    MinecraftServer server=player.getServer();
	    ServerCommandSource consoleSource = server.getCommandSource();
	    String command = String.format("scoreboard players set "+player.getName().getString()+" testPokedex 1");
	    server.getCommandManager().executeWithPrefix(consoleSource, command);
        return 1;
    }
	public static int checkParty(int slot, String pokemon1, String pokemon2, String pokemon3, String pokemon4, String pokemon5, String pokemon6, CommandContext<ServerCommandSource> context) {
	    ServerCommandSource source = context.getSource();
	    ServerPlayerEntity player = source.getPlayer();
	    PartyStore party = Cobblemon.INSTANCE.getStorage().getParty(player);
	    
	    try {
	        String[] pokemon = {pokemon1, pokemon2, pokemon3, pokemon4, pokemon5, pokemon6};
	        boolean[] flags = {false, false, false, false, false, false};
	        
	        if (slot != -1) {
	            if (party.get(slot) == null) {
	                LOGGER.info("El slot " + slot + " del party es nulo.");
	                return 0;
	            }
	            String name = (party.get(slot).getSpecies() != null) ? party.get(slot).getSpecies().getName().toLowerCase()  : null;
	            LOGGER.info("NAME: " + name);
	            if (name == null) {
	                return 0;
	            }
	            for (int i = 0; i < 6; i++) {
	                if (pokemon[i].equals("")) {
	                    flags[i] = true;
	                } else {
	                    if (name.equals(pokemon[i].toLowerCase())) {
	                        flags[i] = true;
	                    }
	                }
	            }
	        } else {
	            for (int i = 0; i < 6; i++) {
	                if (pokemon[i].equals("")) {
	                    flags[i] = true;
	                } else {
	                    for (int j = 0; j < party.size(); j++) {
	                        if (party.get(j) == null) {
	                            continue;
	                        }
	                        String name = (party.get(j).getSpecies() != null) ? party.get(j).getSpecies().getName().toLowerCase() : null;
	                        LOGGER.info("NAME: " + name);
	                        if (name != null && name.equals(pokemon[i].toLowerCase())) {
	                            flags[i] = true;
	                            break;
	                        }
	                    }
	                }
	            }
	        }	        
	        if (flags[0] && flags[1] && flags[2] && flags[3] && flags[4] && flags[5]) {
	            MinecraftServer server = player.getServer();
	            ServerCommandSource consoleSource = server.getCommandSource();
	            String command = String.format("scoreboard players set %s testParty 1", player.getName().getString());
	            server.getCommandManager().executeWithPrefix(consoleSource, command);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        player.sendMessage(Text.literal("Error inesperado: " + e.getMessage()), false);
	        return 0;
	    }
	    return 1;
	}

	public static int indexOfContains(String[] array, String target) {
	    return IntStream.range(0, array.length)
	                    .filter(i -> array[i].toLowerCase().contains(target))
	                    .findFirst()
	                    .orElse(-1);
	}
	
}