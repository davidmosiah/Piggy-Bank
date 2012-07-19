package com.delx.scripts;

import android.content.Context;

import com.delx.database.RepositorioDica;
import com.delx.util.SQLiteHelper;

public class RepositorioDicaScript extends RepositorioDica {

	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS dica";

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
	"create table dica ( _id integer primary key autoincrement,titulo text not null, dica text not null, favorito text not null);",
	"insert into dica(titulo, dica, favorito) values('Banks','Check the banks for a different interest rates for both savings and loans. Dont just go with anyone go with anyone, get the best rates possible.','n');",
	"insert into dica(titulo, dica, favorito) values('Sell Sell Sell','Everybody has unwanted goods lying around their home gathering dust - Well, you might as well sell them and with the cash you get you can invest for the future.','n');",
	"insert into dica(titulo, dica, favorito) values('Budget','The first way to save money is to identify what you have to spend and what your weekly costs are. Budgets arent boring, saving hard earn cash can be exciting when you have a long term plan. ','n');",
	"insert into dica(titulo, dica, favorito) values('Eat At Home','Stop eating out and start eating at home you will save a lot this way and you will know what is in the food. Help save money by preparing your food at home and creating fresh food.','n');",
	"insert into dica(titulo, dica, favorito) values('Free Stuff ','Enter rewards programs to get free stuff, but be discerning some schemes dont offer anything you want and some you have to spend too much to receive a reward. Just go with the ones that give you points for spending on what you normally would purchase. Instead of saving on purchases, you can avoid having to pay altogether. ','n');",
	"insert into dica(titulo, dica, favorito) values('Haggle Existing Loans','Threaten to take your business elsewhere and youd be surprised how flexible lending institutes can be.','n');",
	"insert into dica(titulo, dica, favorito) values('Repair Things','Repair your goods before they become very expensive to replace. Wait too long and it may cost you dearly. ','n');",
	"insert into dica(titulo, dica, favorito) values('Quit Vices','Quit smoking and other vices that not only hurt your health, but they also cost your a lot of cash in the long run. ','n');",
	"insert into dica(titulo, dica, favorito) values('Leave Your Money At Home','Dont take your wallet or purse with you when you go window shopping. At worst take a few dollars with you but leave the plastic at home.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Buy Generic','Often generic brands are equal in quality to their higher priced competition. Buy Generic and save cash. One of the classic strategies to save money  ','n');",
	"insert into dica(titulo, dica, favorito) values('Bake A Cake','Actually, bake anything you want - The more the better - cakes, bread and cookies they taste better too ','n');",
	"insert into dica(titulo, dica, favorito) values('Evaluate Grocery Items','Are the items you buy useful? Dont just buy from habit, make educated product comparisons and you will save money in the long run.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Make Large Meals And Freeze leftovers','By making extra you can save on waste and avoid extra electricity costs.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Pack Your Own Lunch','Dont buy lunch every day or you ll rack up the costs when you could save money by making lunch yourself.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Buy A Slow Cooker','Having a slow cooker means you can use cheaper cuts of meat and still have tender, delicious meals.','n');",
	"insert into dica(titulo, dica, favorito) values('Home Brew','Make your own beer its fun and you will save a lot of money.','n');",
	"insert into dica(titulo, dica, favorito) values('Use Power Saving Appliance','Keep your power bills down by using energy saving appliances. ','n');",
	"insert into dica(titulo, dica, favorito) values('Buy Goods In Bul','Bulk buy stores are great just dont buy too much or you may just end up throwing food away.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Pay Bills Online','Why drive to the store to pay bills when you can save fuel and pay online. ','n');",
	"insert into dica(titulo, dica, favorito) values('Buy A Home Gym','You will save money on gym fees in no time.','n');",
	"insert into dica(titulo, dica, favorito) values('Rent Library Books','Why pay $30 for a book you will only read once? - Rent books from the library and save money.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Use A Cheaper Razor','Expensive razors dont necessarily do a better job than the far less expensive kind.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Consolidate Your Debt','If you can consolidate your debt to save money then you should do so immediately. ','n');",
	"insert into dica(titulo, dica, favorito) values('Carpool','Save money on fuel and save the environment by carpooling with colleagues.','n');",
	"insert into dica(titulo, dica, favorito) values('Grow Your Own','If you can grow your own fruit and vegetables then you should give it a try because you can benefit from delicious organic produce and save money. Many people start out using this as one of their favorite strategies to save money. Maybe you will too?  ','n');",
	"insert into dica(titulo, dica, favorito) values('Use Less Make Up','Dont use make up at least two days a week and you will save over 25% on make up costs ','n');",
	"insert into dica(titulo, dica, favorito) values('Use The Internet','Find instructions off the internet and make as many repairs as you possibly can and you ll avoid having to hire costly experts.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Read Junk mail','If you can handle the temptation then look through your advertising mail for specials and coupons that can save you moolah. ','n');",
	"insert into dica(titulo, dica, favorito) values('Stay Healthy','Exercise and eat right because healthier people have lower medical expenses - It must be true then that healthier = wealthier.....Maybe? ','n');",
	"insert into dica(titulo, dica, favorito) values('Maintain Your Vehicle','Pump up your tires and clean your air filter to increase fuel efficiency and youll save money on rising fuel costs.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Barter/Bargain/Haggle','If you try to bargain down every single purchase you make then you will save a phenomenal amount of money. You wont believe how successful a little haggling can be.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Use Energy Saving Light Bulbs','Save money on energy costs. ','n');",
	"insert into dica(titulo, dica, favorito) values('Turn Off The Lights','Create a little ambience and save energy. Never leave the light on when you leave a room.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Second Hand','Dont be afraid to investigate the savings you can reap from buying previously owned goods, you can pick up great deals if you are prepared to spend the time searching.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Fix It Now','Dont let things that need repairing disintegrate further, be pro-active and fix them now before it ends up costing you dearly.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Find Free Activities','If the best things in life are free then you can look forward to a world of possibilities. Parks, beaches, mountains and country sides offer a variety of locations to enjoy the great outdoors and the best part is most outdoor activities are good for your physical health.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Style Your own Hair','This doesnt have to mean you just do your own, you can cut your whole families hair and save money. You can style a friends hair and let them style yours in return.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Pack A Drink','Pack a drink whenever you intend to go somewhere and avoid having to pay $4 for a bottle of water from a convenience store. Convenience stores may have convenient locations but the prices are usually anything but.  ','n');",
	"insert into dica(titulo, dica, favorito) values('Drive Smarter','Speeding burns more fuel so be a smarter driver and reduce over accelerating and braking to maximize efficient driving. ','n');",
	"insert into dica(titulo, dica, favorito) values('Net Coupons','Use the internet to find coupons online and you will save money, but be careful to only purchase products that you would otherwise buy regardless of the discount. ','n');",
	"insert into dica(titulo, dica, favorito) values('Camping Vacations','Go camping for your next vacation and you ll save money on those exorbitant hotel fees and get in touch with nature at the same time - camping makes for a great family vacation. ','n');",
	"insert into dica(titulo, dica, favorito) values('Turn Off Appliances','Appliances can use electricity even when you are not using them so turn them off at the wall to save on your energy bill. It s an energy saving strategy and its one of the best strategies to save money. ','n');",
	"insert into dica(titulo, dica, favorito) values('Have A Loose Change Jar','Never spend coins! - Only ever break notes and when you receive change, put it in a loose change jar and you will amass a nice little nest egg.','n');",
	"insert into dica(titulo, dica, favorito) values('Maximize Your Tax','Leave no stone unturned in the quest to maximize your tax benefits. Seeking professional help is always a good step to find out the information you need to keep as much of you hard earned money as you can. ','n');",
	"insert into dica(titulo, dica, favorito) values('Bike','Leave the car at home and bike to work you will fatten your bank balance while you shrink your waistline. ','n');",
	"insert into dica(titulo, dica, favorito) values('Use Your Computer','Make birthday cards, photos and anything you can to utilize your computers money saving functions. ','n');",
	"insert into dica(titulo, dica, favorito) values('Know Your Supermarket','Find out when your supermarket reduces perishable items and be sure to visit during these cheaper times to purchase your milk and bread.','n');",
	"insert into dica(titulo, dica, favorito) values('Bundle Your Phone','If you can get a good phone deal then it may be worth bundling your land line, cellphone/mobile and your internet connection to save money. ','n');",
	"insert into dica(titulo, dica, favorito) values('Clean Your Dryer','Clean the filter in your clothes dryer and your dryer will be more energy efficient and it wont catch fire and burn the house down....YAY! ','n');",
	"insert into dica(titulo, dica, favorito) values('Bundle Your Insurance','If and only if you can get a good deal you should bundle your collective insurances with one company to receive maximum savings.','n');",
	"insert into dica(titulo, dica, favorito) values('Never boil a full kettle','Why boil a full jug of water for just one cup? - A waste of electricity and an extra cost. ','n');",
	"insert into dica(titulo, dica, favorito) values('SUE EVERYBODY','Just Kidding - Seriously do not become a serial court junkie, put that lawyer away! ','n');",
	"insert into dica(titulo, dica, favorito) values('Use Rewards Schemes','Use them smart and use them often to receive either savings or bonus products but do not shop somewhere expensive just to gain 10 points worth around 12 cents. ','n');",
	"insert into dica(titulo, dica, favorito) values('Board games','Board Games, a cheap and entertaining alternative to expensive video games. ','n');",
	"insert into dica(titulo, dica, favorito) values('Buyers Clubs','Making large purchasers with other consumers can entitle you to sizable discounts. Some buyers clubs organize purchases for you and some are better than others so research is recommended. ','n');",
	"insert into dica(titulo, dica, favorito) values('Home Cafe','Out of 101 money saving tips this may seem the most obvious, but apparently not to some. Have coffee at home and avoid paying those high coffee house prices - this one action can save you $1500 a year if you are an avid coffee drinker. ','n');",
	"insert into dica(titulo, dica, favorito) values('Movie Treats','Take your own sweet treats to the movie theaters and you will save a lot on the price of movie theater candy. ','n');",
	"insert into dica(titulo, dica, favorito) values('Free Newspaper','Visit the library to read the newspapers and you can save a dollar a day.','n');",
	"insert into dica(titulo, dica, favorito) values('Avoid Small Stores','Friendly, personal service comes with a price tag and often a high price tag so try to shop at cheaper larger stores. So if need a social experience, by all means shop small, but not if you are budgeting your money. ','n');",
	"insert into dica(titulo, dica, favorito) values('Look Low','When shopping look low for the cheapest deals as the eye level products are always the most expensive. ','n');",
	"insert into dica(titulo, dica, favorito) values('Never Shop Hungry','People who shop on an empty stomach buy more than they need and they also buy excess junk food. ','n');",
	"insert into dica(titulo, dica, favorito) values('Contain Your Impulses','Impulse buying often leads to buyers regret and smaller bank balances. ','n');",
	"insert into dica(titulo, dica, favorito) values('Be Strategic','When shopping online be very strategic and thorough to find the best deals. Look for minimal postage costs and weigh up the convenience-cost factors. ','n');",
	"insert into dica(titulo, dica, favorito) values('Record Movies','Record Television movies during the week to have a good supply for the weekend and save money on d.v.d rentals. ','n');",
	"insert into dica(titulo, dica, favorito) values('Do not Buy Travel Books','You probably can find all the information online and print out anything of value.','n');",
	"insert into dica(titulo, dica, favorito) values('Make Use Of Birthdays','Many restaurants offer free meals to the birthday recipient, so be sure to cash in on these type of offers, but do not proceed to have 5 birthdays this week - it is poor form. ','n');",
	"insert into dica(titulo, dica, favorito) values('Wash Your Car','Wash it yourself and save $15 on a 10 minute drive thru car wash, you(probably) do not earn $90 an hour so do not pay that for a car wash. ','n');",
	"insert into dica(titulo, dica, favorito) values('Change Money At A Bank','When converting foreign currency do it at a bank as the kiosks usually have worse rates. ','n');",
	"insert into dica(titulo, dica, favorito) values('Monitor Fuel Prices','Some towns/cities have fuel updates on the radio and some even have websites dedicated to highlighting the cheapest fuel at gas stations. Keep an eye out for the cheapest fuel near you. ','n');",
	"insert into dica(titulo, dica, favorito) values('Happy Hour','If you are going to drink then drink during happy hour - but do not get too happy.','n');",
	"insert into dica(titulo, dica, favorito) values('House Swapping Vacations','Do not pay for a hotel in another city when you can swap houses with someone else and have a super cheap vacation away - Without the hefty price tag.','n');",
	"insert into dica(titulo, dica, favorito) values('Off Peak Rates','If you want to stay in a hotel then try to go in off peak times to save money on costs.','n');",
	"insert into dica(titulo, dica, favorito) values('Insulate','Keep your home well insulated to save money on both heating and cooling costs. ','n');",
	"insert into dica(titulo, dica, favorito) values('Only Wash Large Loads','This goes for both clothes washing and dish washing - Only wash big full loads to increase your energy efficiency. ','n');",
	"insert into dica(titulo, dica, favorito) values('Take Shorter Showers','Reduce the time you spend in the shower or shower with someone else to reduce heating costs. ','n');",
	"insert into dica(titulo, dica, favorito) values('Drop Call Waiting','Get rid off it if it costs you money - most of us do not need it, so drop it unless it is free. Sometimes budgeting your money means you need to trim the fat. ','n');",
	"insert into dica(titulo, dica, favorito) values('Change Your Own Oil Filter','Change Your Own Oil Filter.It is easy to do and you will save at least $25. Instructions can be found on the internet. ','n');",
	"insert into dica(titulo, dica, favorito) values('Clean Your Own Carpet','Clean Your Own Carpet. Do-it-yourself, hire a machine and clean your own carpets - You might even do a more thorough job and you will help save money. ','n');",
	"insert into dica(titulo, dica, favorito) values('Mow Your Own Lawns','Get some exercise and save money at the same time. ','n');",
	"insert into dica(titulo, dica, favorito) values('Evaluate Your Need For Cable T.V','At the very least cancel the channels that you do not watch but also evaluate your need for pay T.V at all. ','n');",
	"insert into dica(titulo, dica, favorito) values('Keep An Eye On Ebay','Keep An Eye On Ebay. From time to time you can find gift vouchers for sale at a fraction of the cost. Some vouchers may be redeemable at the supermarket which means if you manage to find a $50 voucher for $25 you can save $25 of your groceries and start saving on purchases. ','n');",
	"insert into dica(titulo, dica, favorito) values('Use Email','Instead of the post or sms or phone - Use email, it is free and convenient.','n');",
	"insert into dica(titulo, dica, favorito) values('Buy White Sheets','Buy White Sheets. They last for an eternity and if they get stained you can always just bleach them. Okay, so this is probably one of the most boring of the strategies to save money, but it is good nonetheless. ','n');",
	"insert into dica(titulo, dica, favorito) values('Cider Vinegar','Cider Vinegar is a cheap alternative to cleaning products as it has multiple uses and is effective. ','n');",
	"insert into dica(titulo, dica, favorito) values('Cheap Tuesday','Cheapskate Tuesday or tightwad Tuesday - It does not matter what you call it, you can save money on this el cheapo day. ','n');",
	"insert into dica(titulo, dica, favorito) values('Find Instruction Websites','Use websites to find free information that can save you money such as  how to  style sites ','n');",
	"insert into dica(titulo, dica, favorito) values('Post Easter Chocolate','If your a chocolate lover then the time to purchase a yearly supply is directly after Easter when both shops have a chocolate surplus and customers are sick of the sight of chocolate. ','n');",
	"insert into dica(titulo, dica, favorito) values('Pay Bills On time','Late fees are a pointless expense especially if you have just procrastinated and left it too late. PAY ON TIME and avoid penalties. ','n');",
	"insert into dica(titulo, dica, favorito) values('Avoid Vending Machines','They have pretty lights and tasty snacks and the child within us just loves them but they are always expensive and usually unhealthy. If you are budgeting your money then stay away from the pretty packages. ','n');",
	"insert into dica(titulo, dica, favorito) values('B.Y.O','If a restaurant allows you to bring in your own beverages then do so because you will skip having to pay  restaurant  drink prices and help save money. ','n');",
	"insert into dica(titulo, dica, favorito) values('Beware Bottled Water','Be aware that a lot of the bottled water for sale is straight out of a hose and is neither spring water nor filtered, so be educated if you are going to spend $4 for hose water. ','n');",
	"insert into dica(titulo, dica, favorito) values('Watch Out On Extended Warranties','All good products should have a reasonable warranty, but many stores will try and sell you a an expensive extended warranty - Be very careful of these deals as the replacement value will drop every year and may be cheaper than a warranty. ','n');",
	"insert into dica(titulo, dica, favorito) values('Refill Your Own Ink','Refill Your Own Ink. Buy an ink cartridge kit to save replacing them at a premium cost and to help save money. ','n');",
	"insert into dica(titulo, dica, favorito) values('Listen To Songs Free','There are many public domain songs on you tube and other social sites, many new bands have free downloads of their music to gain a following, take advantage of these legal free gifts. ','n');",
	"insert into dica(titulo, dica, favorito) values('Make Your Own Popcorn','Buy loose popcorn and microwave in a large bowl and save 500% on packet popcorn costs. ','n');",
	"insert into dica(titulo, dica, favorito) values('Try A VOIP Type Service','Try A VOIP Type Service .These services enable you to make cheap phone calls using your internet connection. ','n');",
	"insert into dica(titulo, dica, favorito) values('Visit The Markets','Fresh produce markets are a great way to find seasonable goods at wholesale prices. ','n');",
	"insert into dica(titulo, dica, favorito) values('Why Buy Magazines?','The internet has all the gossip and pictures of celebrities that you could ever hope for. The magazine companies probably do not appreciate these saving money tips. ','n');",
	"insert into dica(titulo, dica, favorito) values('Watch Ebay For Bargains','Every now and then genuine bargains come around and you have to be aware to take advantage of them ','n');",
	"insert into dica(titulo, dica, favorito) values('Careful Of Automatic Tellers','Be sure to draw money out of your banks branded A.T.M to avoid paying excess fees and always count withdrawn money before you walk away from the machine so the camera can see if there is a case of you receiving the wrong amount. ','n');",
	"insert into dica(titulo, dica, favorito) values('Make Your Own Ice Cream','The Yummiest money saving tip of all and it is so easy to do. Even If you do not have an ice cream maker all you need is a bowl of ice and a metal bowl and you can stir the mixture as it freezes. The best part is you can create your own delicious flavors - even completely oddball ones like peanut butter-mint or orange-berry-bubblegum ????????','n');"

	
	};

	// Nome do banco
	private static final String NOME_BANCO = "delx_dica";

	// Controle de vers�o
	private static final int VERSAO_BANCO = 2;

	// Nome da tabela
	public static final String TABELA_CARRO = "dica";

	// Classe utilit�ria para abrir, criar, e atualizar o banco de dados
	private SQLiteHelper dbHelper;

	// Cria o banco de dados com um script SQL
	public RepositorioDicaScript(Context ctx) {
		// Criar utilizando um script SQL
		dbHelper = new SQLiteHelper(ctx, RepositorioDicaScript.NOME_BANCO, RepositorioDicaScript.VERSAO_BANCO,
				RepositorioDicaScript.SCRIPT_DATABASE_CREATE, RepositorioDicaScript.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar tamb�m
		db = dbHelper.getWritableDatabase();
	}

	// Fecha o banco
	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}
