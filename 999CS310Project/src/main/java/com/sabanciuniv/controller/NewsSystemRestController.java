package com.sabanciuniv.controller;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Author;
import com.sabanciuniv.model.Category;
import com.sabanciuniv.model.Comment;
import com.sabanciuniv.model.Article;
import com.sabanciuniv.model.ArticlePayload;

import com.sabanciuniv.repo.AuthorRepository;
import com.sabanciuniv.repo.CategoryRepository;
import com.sabanciuniv.repo.CommentRepository;
import com.sabanciuniv.repo.ArticleRepository;

@RestController
@RequestMapping("/newssystem")
public class NewsSystemRestController {
	
	
	@Autowired private AuthorRepository authorRepository;
	@Autowired private ArticleRepository articleRepository;
	@Autowired private CommentRepository commentRepository;
	@Autowired private CategoryRepository categoryRepository;

	
	private static final Logger logger = LoggerFactory.getLogger(NewsSystemRestController.class);
	
	@PostConstruct
	public void init() {
		
		if(authorRepository.count() ==0) {
			logger.info("Database is empty, initializing..");

			
			Author a1 = new Author("Stan","Marsh");
			Author a2 = new Author("Eric","Cartman");
			Author a3 = new Author("Kyle","Broflovski");
			
			authorRepository.save(a1);
			authorRepository.save(a2);
			authorRepository.save(a3);
			
			
			List<Author> authors = authorRepository.findAll();
			
			Category cat1 = new Category("Sports");
			Category cat2 = new Category("Climate");
			Category cat3 = new Category("Economy");
			Category cat4 = new Category("Science-Tech");
			
			categoryRepository.save(cat1);
			categoryRepository.save(cat2);
			categoryRepository.save(cat3);
			categoryRepository.save(cat4);
			
			
			List<Category> categories = categoryRepository.findAll();
			
			
			Comment com1 = new Comment("My thoughts and prayers with them.", "internet mom");
			Comment com3 = new Comment("Hindsight is 20/20", "extremelyOnline");
			Comment com2 = new Comment("Can't get any worse, surely!!", "anonymous");
			Comment com4 = new Comment("There has been a misunderstanding between the media and the fans. As oppose to the popular belief we still love and support the organization, for the right price", "manCityEnjoyer");
			Comment com5 = new Comment("I can't deal with this, why? Another weekend another Ferrari blunder.", "oooopss");
			Comment com6 = new Comment("My career is finished, gone, thanks AI, I appreciate it.", "iStartedCodingAt6");
			Comment com7 = new Comment("This coin gonna get skyrocketed, let me know when you get rich", "cryptoGourou");
			Comment com8 = new Comment("This is the time to invest", "theEconomist");
			Comment com9 = new Comment("Such talent! I am following him from now on.", "positiveCritic");
			
			commentRepository.save(com1);
			commentRepository.save(com2);
			commentRepository.save(com3);
			commentRepository.save(com4);
			commentRepository.save(com5);
			commentRepository.save(com6);
			commentRepository.save(com7);
			commentRepository.save(com8);
			commentRepository.save(com9);
			
			List<Comment> comments = commentRepository.findAll();
			
			
			String dummy_url = "https://www.hdwallpaper.nu/wp-content/uploads/2015/02/Funny-Cat-Hidden.jpg";

			Article art1 = new Article("Canada could hit ‘record levels’ of area burnt by wildfires this year", 2022, 5.0f, 6, "Canada could see a “record” level of land burnt amid an “unprecedented” wildfire season this year, government officials are warning.\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "Nine provinces and territories are battling blazes that have forced the evacuation of more than 100,000 people across the country since early May, officials said in an update Monday.\r\n"
					+ "\r\n"
					+ "New modelling released June 5 shows that the risks of wildfires are set to increase this month and remain “unusually high” throughout the summer in Canada, Natural Resources Minister Jonathan Wilkinson said.\r\n"
					+ "\r\n"
					+ "“Every province and territory will need to be on high alert throughout this wildfire season,” he told reporters, saying that additional firefighting resources will be required moving forward.\r\n"
					+ "\r\n"
					+ "“While this is not yet Canada’s most severe fire season, if this trajectory continues, it very well could be.”\r\n"
					+ "\r\n"
					+ "Since the start of the year, 2,214 wildfires have already burned 3.3 million hectares of land – which is “10 times” the normal average for the season, Wilkinson said.\r\n"
					+ "\r\n"
					+ "As of June 4, more than 400 active fires were burning across the country, the Canadian Interagency Forest Fire Centre reported." , categories.get(1), comments.subList(0, 2), authors.get(0), "https://globalnews.ca/wp-content/uploads/2023/06/nova-scotia-wildfires-federal-update-e1685960619292.jpg?quality=85&strip=all&w=336" );
			Article art2 = new Article("Bitcoin price plummets amid major DOJ crypto announcement", 2018, 2.5f, 8, "The price of bitcoin fell suddenly by 5 per cent in the space of an hour after the US Department of Justice revealed plans to make a major cryptocurrency-related announcement on Wednesday.\r\n"
					+ "\r\n"
					+ "The world’s leading crypto dropped from $21,500 to around $20,500 ahead of the news, reversing an upward trend that had seen its price rise by nearly a third since the start of the year.\r\n"
					+ "\r\n"
					+ "The price saw a slight recovery after the news was announced, which related to a crackdown on an illicit crypto exchange.\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "Deputy Attorney General Lisa Monaco led the press conference announcing the action, describing it as a “significant blow” to the crypto crime landscape and for fraudsters and hackers operating within it.\r\n"
					+ "\r\n"
					+ "Anatoly Legkodymov, the alleged founder of the Bitzlato exchange, was arrested in Miami, Florida, on Tuesday and is scheduled to be arraigned in the US District Court for the Southern District of Florida later on Wednesday, the DOJ announced." , categories.get(2), comments.subList(6, 8), authors.get(2), "https://static.independent.co.uk/2022/11/10/12/bitcoin%20price%20latest%202022%20crash.jpg?quality=50&width=375&auto=webp");
			Article art3 = new Article("Ferrari apologises to Leclerc for Monaco GP qualifying miscommunication", 2023, 3.4f, 10,"Ferrari Formula 1 boss Fred Vasseur says that he apologised to Charles Leclerc for the miscommunication that cost the Monegasque driver three grid positions at the Monaco Grand Prix.Leclerc was penalised for impeding Lando Norris in the tunnel in Q3 after his team failed to warn him early enough about the fast-approaching McLaren driver.\r\n"
					+ "\r\n"
					+ "The penalty dropped Leclerc from third on the grid to sixth, making his life much harder for Sunday's race, in which he eventually finished in the same position.\r\n"
					+ "\r\n"
					+ "Leclerc, who has regularly had bad luck in Monaco, tried to be positive after a disappointing afternoon.\r\n"
					+ "\r\n"
					+ "\"Yesterday I got the information too late and that of course put us a little bit on the back foot for today,\" he said when asked about the penalty by Motorsport.com.\r\n"
					+ "\r\n"
					+ "\"Of course it's frustrating. Any weekend like this one anywhere it's frustrating, at home it's even more frustrating. But on the other hand that's where we need to keep our head up.\r\n"
					+ "\r\n"
					+ "\"The season is long, it's not the only race on the calendar even though it probably means a bit more than the others. The points are the same, so we need to restart again in Barcelona, hopefully having a great weekend that time.\"\r\n"
					+ "\r\n"
					+ "Vasseur said that the team would try to learn from the communication mistake and ensure that there is no repeat." , categories.get(0), comments.subList(4, 5), authors.get(2), "https://cdn-7.motorsport.com/images/amp/68yl4590/s1200/formula-1-monaco-gp-2023-charl-2.webp");
			Article art4 = new Article("Fans paid to attend World Cup by Qatar have daily allowance cancelled", 2020, 4.5f, 12,  "Fans who have travelled to Qatar as part of a controversial paid-for supporters programme have been told by Qatari authorities that their cash has been cut.\r\n"
					+ "\r\n"
					+ "The Fan Leader Network is a scheme run by the Supreme Committee for Delivery and Legacy, the Qatari agency responsible for the World Cup. It has recruited supporters from around the globe, offering travel and accommodation and a place at the World Cup opening ceremony in return for enthusiasm and positive social media content. But the Guardian can reveal that a per diem payment for food and drink, upon which some supporters were depending, was cancelled just as fans were packing to travel to the Gulf.\r\n"
					+ "\r\n"
					+ "Members of the Fan Leader Network from two European countries said their payments had been cancelled three days ago and that authorities had blamed the decision on the bad press which followed the revelation that fans were being paid.Fans were told in a message, seen by the Guardian: “Due to the recent developments in the media, we are keen to protect our visiting fans from the erroneous misinformed statements regarding ‘fans receiving payment for the trip’. Accordingly, the daily allowance will unfortunately no longer be issued. The allowance was intended as a small uplift on your own personal funds to assist with refreshments during your stay.”\r\n"
					+ "\r\n"
					+ "Although the fans the Guardian spoke to said the loss of money had not deterred anyone from travelling, they were concerned about how they would pay for the rest of their stay. One fan said they had paid for maintenance for their car on the assumption that the per diem would be coming.\r\n"
					+ "\r\n"
					+ "The email sent to members of the Network said: “We requested from the outset that you brought sufficient funds to cover your own living expenses and we have committed to cover flights, accommodation and opening match tickets.”\r\n"
					+ "\r\n"
					+ "The news comes two days before the opening ceremony and follows an announcement by Fifa that it would no longer be possible to buy alcohol at World Cup stadiums. This was a decision widely understood to have been forced on football’s governing body at the last minute by Qatar.\r\n"
					+ "\r\n"
					+ "Concerns will now rise that further commitments made by the organisers could yet be ignored too, including the safety of LGBTQ+ fans in a country where homosexuality is illegal.\r\n"
					+ "\r\n"
					+ "According to the terms and conditions of the Fan Leader Network, initially revealed by the Dutch broadcaster NOS, travellers have been asked to promote the tournament and the experience as part of the trip. Key to the deal will be “‘liking’ and re-sharing third-party posts”, and fans have reportedly been asked to flag social media content critical of the event." , categories.get(0) , comments.subList(3, 4), authors.get(1), "https://i.guim.co.uk/img/media/40f558867316153f0c0faf2a03d6b8e717d5ed7c/0_472_8640_5184/master/8640.jpg?width=620&quality=45&dpr=2&s=none");
			Article art5 = new Article("Why do we even have a third-place playoff at the World Cup?", 2022, 3.9f, 3, "It’s the game that no one wants to play in, it’s the game few may even want to watch.\r\n"
					+ "\r\n"
					+ "The World Cup third-place playoff is settling for second best. Well, third best.These are teams who have gone all-out, who have trained for years to get to this tournament. These are players who touched down in Qatar with dreams. Not one of the squad members across the 32 teams in Qatar took the field just to show their face. They came to win, to be the best in the world.\r\n"
					+ "\r\n"
					+ "Playing for third? Why bother? The dream is over, the trophy is beyond reach.\r\n"
					+ "\r\n"
					+ "So, why do we even have a third-place playoff at the World Cup?“Typically, the third-place playoff provides a mere footnote, or at best some symmetry to decide the better of the two semifinalists vanquished by the last two remaining teams left in the tournament, upon whom the eyes of the world will be as they compete to be crowned world champions,” David Webber, a researcher into the cultural and political economy of football at Solent University, told Al Jazeera.\r\n"
					+ "\r\n"
					+ "“But [it] does, however, serve a sporting purpose. At the very least, a shot at redemption, and an opportunity to celebrate the achievement of reaching the last four of the competition. Rarely are these games short on entertainment either, with four out of the last seven bronze medal games, since 1994, seeing four goals or more. With nothing to lose, teams will often go on the attack – with strikers after the Golden Boot often the main beneficiaries.”\r\n"
					+ "\r\n"
					+ "This “bronze medal” idea plays into a wider sense of sporting history.\r\n"
					+ "\r\n"
					+ "“The origin of the World Cup was very much influenced by the Olympics, and the ideology there was always gold, silver and bronze,” said Paul Widdop, a sport business academic at the University of Manchester. “This is then reflected in the way the World Cup is organised. It’s similar to why we have a four-year World Cup cycle, based on outdated Greek mythology.”" , categories.get(0), comments.subList(1, 1), authors.get(1), "https://www.aljazeera.com/wp-content/uploads/2022/12/SOR2803.jpg?resize=770%2C513&quality=80");
			Article art6 = new Article("Lakes Are Drying Up All Over the World", 2022, 5.0f, 6, "Over half the world's lakes are drying up, scientists have found, and it's mostly our fault.\r\n"
					+ "\r\n"
					+ "According to a new paper published on Thursday in the journal Science, 53 percent of lakes worldwide have shrunk between 1992 and 2020.\r\n"
					+ "\r\n"
					+ "This level of water loss is equivalent to 17 Lake Meads, the authors say, which is the largest reservoir by volume in the U.S.\r\n"
					+ "\r\n"
					+ "\"We would say this is a global pattern of drying,\" lead author Fangfang Yao, a CIRES (Cooperative Institute for Research in Environmental Sciences) visiting fellow at the University of Colorado Boulder, and climate fellow at the University of Virginia, told Newsweek. \"The drying is evident in both arid and humid regions, such as western Central Asia, the Middle East, western India, eastern China, northern and eastern Europe, Oceania, the conterminous United States, northern Canada, southern Africa, and most of South America.\"\r\n"
					+ "\r\n"
					+ "To get to this conclusion, Yao and his fellow authors analyzed 250,000 lake-area snapshots of 1,972 of Earth's biggest lakes—comprising 95 percent of lake water on the planet—captured by satellites over the past three decades. They also used long-term water level records to reduce uncertainty in their data.\r\n"
					+ "\r\n"
					+ "\"We combined water areas mapped from satellite imagery and water levels estimated from satellite altimeters to construct near-monthly lake volume time series from 1992 to 2020. Based on the time series data, we estimated the trends for Earth's large water bodies. We de-seasonalized the time series data and thus the seasonal fluctuations were removed before calculating the trends,\" Yao said.Many lakes across the U.S. have also suffered from water losses in recent years, as a result of the ongoing megadrought plaguing the region. Lake Mead reached record lows in the summer of 2022, potentially heading towards dead pool levels later this year, and the Great Salt Lake also hit a record-low water level a few months ago, having lost 73 percent of its water.\r\n"
					+ "\r\n"
					+ "Unfortunately, as with so many rapid changes to the climate in recent years, these worldwide lake water losses are driven by human action.\r\n"
					+ "\r\n"
					+ "\"More than half of the net water loss in natural lakes is attributable to direct human impacts (i.e. human consumption) and indirect human impacts (e.g. climate warming),\" Yao said. \"We are not confident to project the trends into the future, which requires additional efforts. For about 100 drying (large) lakes that were largely driven by warming, it is likely that the drying trends will be continued under a warmer climate.\"\r\n"
					+ "\r\n"
					+ "This huge drop in lake water across the world in only 30 years is bad news: freshwater lakes are responsible for storing 87 percent of the planet's fresh water, and provide millions of people with essential drinking water and agricultural water.\r\n"
					+ "\r\n"
					+ "\"We did not quantify the impacts of drying lakes. Instead, we estimated the number of populations residing in a basin with a drying lake. We found roughly one-quarter of the world's population residing in these basins,\" Yao explained.\r\n"
					+ "\r\n"
					+ "\"Widespread LWS [lake water surface] decline, particularly accompanied by rising lake temperatures, could reduce the amount of absorbed carbon dioxide and increase carbon emissions to the atmosphere given that lakes are hotspots of carbon cycling. Drying lakes can cause freshwater loss, environmental degradation (e.g., receding shorelines, increasing salinity, deteriorating water quality, associated with level declines). A drying hydroelectric reservoir may lead to a reduction in hydropower energy generation. There may be other impacts on navigation, recreation...,\" Yao said.Additionally, the drying of lakes could be harder to manage and mitigate, due to the bureaucracy surrounding refilling lakes.\r\n"
					+ "\r\n"
					+ "\"Lakes are often used for water supply as stand-alone resource,\" Balaji Rajagopalan, co-author of the paper and professor of hydrology and water resources at the University of Colorado, Boulder, told Newsweek. \"This leaves the management to local entities. As a result, they are not integrated with other water resources managed by public entities. This leads to over-exploitation and sub-optimal management.\"\r\n"
					+ "\r\n"
					+ "\"It is hard to resurrect a drying or dried lake. Hence, pre-empting this with smart management is essential. This is especially critical in a warmer world,\" Rajagopalan said.\r\n"
					+ "\r\n"
					+ "Not everything the authors found was bad news, however: they also discovered that 24 percent of lakes actually increased in their water storage, mostly in underpopulated areas in the inner Tibetan Plateau in Nepal and the U.S.'s Northern Great Plains, as well as in areas with newer reservoirs such as the Yangtze, Mekong, and Nile river basins.\r\n"
					+ "\r\n"
					+ "With the current trajectory of climate change, which is forecast to lead to a global average temperature increase of 2.7 degrees Celsius—around 5 degrees Fahrenheit—by the year 2100, this lake drying issue is only likely to get worse.\"Looking ahead, while it's likely that changes in climate will continue, water management and the human consumption part is something that we do have control over,\" Ben Livneh, an associate professor of hydrology at the University of Colorado Boulder and co-author of the paper, told Newsweek.\r\n"
					+ "\r\n"
					+ "\"Solutions must involve a combination of awareness—like what we have raised in this study, better monitoring, as well as actionable management that prioritizes healthy lake levels. You can see good examples of management, like on the Colorado basin where specific water elevations trigger action, whereas bad examples are like the Aral Sea where unsustainable diversion led to a catastrophic loss of one of the world's largest lakes,\" Livneh said." , categories.get(1), comments.subList(1, 2), authors.get(2), "https://d.newsweek.com/en/full/2236294/dried-lake.webp?w=790&f=738e0367765f8cd5375fb0c53d0f4899" );
			Article art7 = new Article("Tarsis Orogot: Uganda's sprinting sensation is 'a boy with a dream' - and really cool socks", 1999, 2.5f, 8, "The national record holder in the 200m chases an NCAA title for Alabama - and much more. He speaks to Olympics.com about his unique path to sprinting, and how Joshua Cheptegei has inspired him.Growing up in Uganda, Tarsis Orogot didn’t have any African sprinters to look up to. And when the teen moved away from home to Nairobi, Kenya, to train full time, a chorus of doubters cautioned him.\r\n"
					+ "\r\n"
					+ "“When I was starting out, many people did not believe that I was going to be able to compete at the right level,” the athletics competitor, who runs in the U.S. collegiate system at Alabama, told Olympics.com.\r\n"
					+ "\r\n"
					+ "“Many coaches and officials told me that I was I was crazy to think that I could compete with the Americans and Europeans in sprint events,” he said. “They advised me that, if I wanted to amount to anything, I would have to do the 800 or 1500m.”\r\n"
					+ "\r\n"
					+ "It spurred a motto that Orogot, who is now 20, still uses today on his Instagram: “Just a boy with a dream.”\r\n"
					+ "\r\n"
					+ "“That’s how the dream started,” he explained. “I wanted to be one of the best in the world in the sprint events.”Tarsis Orogot: One to watch in the 200m\r\n"
					+ "Orogot is on his way to being considered “one of the best in the world” in the 200m today, as he’s turned heads over the last several seasons, beginning with a fourth-place finish at the 2021 World Athletics U20 Championships in Nairobi.\r\n"
					+ "\r\n"
					+ "That event, in fact, marked a shift in his belief. He was running times that would have put him in the Olympic final at Tokyo 2020, which had taken place just a few weeks prior.\r\n"
					+ "\r\n"
					+ "“Once I ran a 20.37 in the semi-finals, I wanted to just go back and start training more,” he said of the realisation.\r\n"
					+ "\r\n"
					+ "It also shifted his anticipated approach: Orogot had plans to go professional after the U20 Champs, but he was in part influenced by the path of Jareem Richards, the 2017 world bronze medallist in the 200m from Trinidad & Tobago, who had competed for Alabama.\r\n"
					+ "\r\n"
					+ "Having conferred with Crimson Tide coach Blaine Wiley, the decision was made: Still a dreaming boy, Orogot would make his way from Uganda via Nairobi to Tuscaloosa, Alabama.\r\n"
					+ "\r\n"
					+ "His second season in Alabama has seen him race to new heights: His 20.20 in February set a new Ugandan record (indoors), and a wind-aided 19.60 gave him the third-fastest collegiate time ever (all-conditions) – and a word of congratulations from world champion Noah Lyles, who he was faster than on the day.\r\n"
					+ "\r\n"
					+ "" , categories.get(0), comments.subList(8, 9), authors.get(2), "https://img.olympicchannel.com/images/image/private/t_s_w1340/t_s_16_9_g_auto/f_auto/primary/svpc1ink00fh9jv8z0ot");
			Article art8 = new Article("Steve Frederickson, Lucy.ai: How AI powers a next-gen ‘answer engine’", 2023, 4.4f, 10,"In an interview at AI & Big Data Expo with Steve Frederickson, Chief Product Officer at Lucy.ai, we gained valuable insights into how AI is powering a next-gen “answer engine” for enterprises.\r\n"
					+ "\r\n"
					+ "Lucy is designed to unlock and harness the vast knowledge residing within a company’s data repositories, regardless of format or source. From SharePoint and Google Drive to Dropbox and third-party tools, Lucy can seamlessly search and connect with all types of content, facilitating efficient knowledge retrieval for employees.\r\n"
					+ "\r\n"
					+ "“Lucy 4, our latest version, is very exciting for us. We went through a significant development phase, incorporating feedback from customers who used Lucy 3. We re-envisioned what knowledge discovery means for large companies,” says Frederickson.\r\n"
					+ "\r\n"
					+ "The team went back to the basics of what an answer engine should be, prioritising the content itself and the individuals who created it. The ultimate aim was to foster new connections and opportunities for collaboration within the enterprise, breaking down silos and facilitating knowledge-sharing across departments.\r\n"
					+ "\r\n"
					+ "When measuring success, Lucy.ai focuses not only on engagement metrics but also on the tangible impact it has on saving employees’ time.\r\n"
					+ "\r\n"
					+ "Through interviews with customers, Frederickson has received feedback emphasising how Lucy has become a time-saving tool within their organisations. One notable outcome has been the breaking down of data silos between different departments and fostering a sense of unity and cooperation across the company.\r\n"
					+ "\r\n"
					+ "The rise of remote work, particularly in a post-pandemic world, has further amplified the need for knowledge-surfacing solutions like Lucy.\r\n"
					+ "\r\n"
					+ "As employees continue to work remotely, maintaining a connection with their company’s knowledge base and colleagues becomes crucial. Frederickson highlighted that employees often resort to reaching out to co-workers directly for information, bypassing the need for traditional search methods.\r\n"
					+ "\r\n"
					+ "To address this challenge, the company developed Lucy Synopsis, a feature that allows users to interact with Lucy as if they were conversing with a co-worker on platforms like Microsoft Teams. By asking Lucy questions in a conversational manner, employees can receive precise answers and even have relevant content presented in an easily understandable format.\r\n"
					+ "\r\n"
					+ "While surfacing information is essential, not all data within a company should be readily accessible to everyone.\r\n"
					+ "\r\n"
					+ "Frederickson addressed this concern by highlighting the robust access controls provided by Lucy. These controls encompass both role-based and attribute-based access, tailored to fit the specific taxonomy and security requirements of each organisation. By aligning with a company’s access levels, Lucy can provide users with answers based on their permissions, ensuring the confidentiality and integrity of sensitive information.\r\n"
					+ "\r\n"
					+ "In a market with several answer-surfacing solutions, Lucy aims to stand out by adopting a holistic approach to the search process.\r\n"
					+ "\r\n"
					+ "The company redefines search as a comprehensive journey, extending beyond the initial question to encompass the entire knowledge cycle.\r\n"
					+ "\r\n"
					+ "“We consider search as an end-to-end journey that goes beyond finding a document. Users may need to identify specific pages, contact document authors for clarification, or contribute contextual information for future reference,” explains Frederickson.\r\n"
					+ "\r\n"
					+ "Lucy recognises the importance of these extended interactions and strives to facilitate them seamlessly within their platform. Furthermore, Lucy AI excels at connecting with various data sources, not limited to internal repositories but also integrating with third-party tools like Confluence and ServiceNow. This versatility allows companies to leverage their existing knowledge repositories while making information accessible through Lucy’s unified interface.\r\n"
					+ "\r\n"
					+ "As an agile startup, Lucy.ai embraces the fast-paced nature of the industry. Frederickson emphasised that maintaining a set of core principles is essential to the company’s success.\r\n"
					+ "\r\n"
					+ "Empowering individuals with knowledge lies at the heart of Lucy.ai’s mission, and they constantly explore how new tools and developments in generative AI can support that objective. By closely engaging with customers and prospects, Lucy.ai stays attuned to their needs and adapts its feature set to align with their evolving policies and requirements.\r\n"
					+ "\r\n"
					+ "Looking ahead, Lucy AI has ambitious plans for the future.\r\n"
					+ "\r\n"
					+ "“We are excited to continue building on the strong foundation of Lucy 4. We aim to foster conversations and connections between departments, using Lucy as a tool to empower people and foster collaboration within the enterprise. We have exciting developments in this area that we look forward to sharing,” says Frederickson.\r\n"
					+ "\r\n"
					+ "Lucy.ai’s innovative approach to knowledge discovery and its commitment to empowering individuals within organisations make it a formidable player in the field. As the remote work trend continues and the need for efficient knowledge surfacing grows, Lucy.ai’s comprehensive answer engine offers a unique solution.\r\n"
					+ "\r\n"
					+ "By bridging the gap between employees and their company’s collective knowledge, Lucy not only saves time and improves productivity, but also facilitates meaningful connections that drive innovation and collaboration within the enterprise." , categories.get(3), comments.subList(5, 6), authors.get(2), "https://www.artificialintelligence-news.com/wp-content/uploads/sites/9/2023/05/steve-frederickson-lucy-ai-big-data-expo-north-america-artificial-intelligence-enterprise-knowledge-surfacing-2048x1365.jpg");
			Article art9 = new Article("An Eating Disorder Chatbot Is Suspended for Giving Harmful Advice)", 2020, 4.5f, 12,  "A NONPROFIT HAS suspended the use of a chatbot that was giving potentially damaging advice to people seeking help for eating disorders. Tessa, which was used by the National Eating Disorders Association, was found to be doling out advice about calorie cutting and weight loss that could exacerbate eating disorders. \r\n"
					+ "\r\n"
					+ "The chatbot’s suspension follows the March announcement that NEDA would shut down its two-decade-old helpline staffed by a small paid group and an army of volunteers. NEDA said yesterday that it has paused the chatbot, and the nonprofit’s CEO, Liz Thompson, says the organization has concerns over language Tessa used that is “against our policies and core beliefs as an eating disorder organization.”The news plays into larger fears about jobs being lost to advances in generative artificial intelligence. But it also shows how harmful and unpredictable chatbots can be. As researchers are still grappling with rapid advances in AI tech and its potential fallouts, companies are rushing a range of chatbots into the market, and real people are put at risk. \r\n"
					+ "\r\n"
					+ "Tessa was paused after several people saw how it responded to even the most straightforward questions. One was Alexis Conason, a psychologist who specializes in eating disorders. In a test, Conason told Tessa that she had gained a lot of weight recently and really hated her body. In response, Tessa encouraged her to “approach weight loss in a healthy and sustainable way,” advising against rapid weight loss and asking if she had seen a doctor or therapist. \r\n"
					+ "\r\n"
					+ "When Conason asked how many calories she should cut a day to lose weight in a sustainable way, Tessa said “a safe daily calorie deficit to achieve [weight loss of 1 to 2 pounds a week] would be around 500-1000 calories per day.” The bot still recommended seeing a dietitian or health care provider. \r\n"
					+ "\r\n"
					+ "Conason says she fed Tessa the kind of questions her patients might ask her at the beginning of eating disorder treatment. She was concerned to see it give advice about cutting added sugar or processed foods, along with cutting calories. “That’s all really contrary to any kind of eating disorder treatment and would be supporting the eating disorder symptoms,” Conason says. \r\n"
					+ "\r\n"
					+ "FEATURED VIDEO\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "Computer Scientist Explains One Concept in 5 Levels of Difficulty\r\n"
					+ "\r\n"
					+ "MOST POPULAR\r\n"
					+ "The Best Nintendo Switch Games for Every Kind of Player\r\n"
					+ "GEAR\r\n"
					+ "The Best Nintendo Switch Games for Every Kind of Player\r\n"
					+ "WIRED STAFF\r\n"
					+ "\r\n"
					+ "What to Expect at Apple’s WWDC 2023\r\n"
					+ "GEAR\r\n"
					+ "What to Expect at Apple’s WWDC 2023\r\n"
					+ "BRENDA STOLYAR\r\n"
					+ "\r\n"
					+ "They Plugged GPT-4 Into Minecraft&#8212;and Unearthed New Potential for AI\r\n"
					+ "BUSINESS\r\n"
					+ "They Plugged GPT-4 Into Minecraft—and Unearthed New Potential for AI\r\n"
					+ "WILL KNIGHT\r\n"
					+ "\r\n"
					+ "Apple’s Mixed-Reality Headset, Vision Pro, Is Here\r\n"
					+ "GEAR\r\n"
					+ "Apple’s Mixed-Reality Headset, Vision Pro, Is Here\r\n"
					+ "LAUREN GOODE\r\n"
					+ "\r\n"
					+ "In contrast to chatbots like ChatGPT, Tessa wasn’t built using generative AI technologies. It’s programmed to deliver an interactive program called Body Positive, a cognitive behavioral therapy-based tool meant to prevent, not treat, eating disorders, says Ellen Fitzsimmons-Craft, a professor of psychiatry at Washington University School of Medicine who worked on developing the program. \r\n"
					+ "\r\n"
					+ "Fitzsimmons-Craft says the weight loss advice given was not part of the program her team worked to develop, and she doesn’t know how it got into the chatbot’s repertoire. She says she was surprised and saddened to see what Tessa had said. “Our intention has only been to help individuals, to prevent these horrible problems.” Fitzsimmons-Craft was an author of a 2021 study that found a chatbot could help reduce women’s concerns about weight and body shape and possibly reduce the onset of an eating disorder. Tessa is the chatbot built on this research.\r\n"
					+ "\r\n"
					+ "Tessa is provided by the health tech company X2AI, now known as Cass, which was founded by entrepreneur Michiel Rauws and offers mental health counseling through texting. Rauws did not respond to questions from WIRED about Tessa and the weight loss advice, nor about glitches in the chatbot’s responses. As of today, the Tessa page on the company’s website was down. \r\n"
					+ "\r\n"
					+ "Thompson says Tessa isn’t a replacement for the helpline, and the bot had been a free NEDA resource since February 2022. “A chatbot, even a highly intuitive program, cannot replace human interaction,” Thompson says. But in an update in March, NEDA said that it would “wind down” its helpline and “begin to pivot to the expanded use of AI-assisted technology to provide individuals and families with a moderated, fully automated resource, Tessa.” \r\n"
					+ "\r\n"
					+ "Fitzsimmons-Craft also says Tessa was designed as a separate resource, not something to replace human interaction. In September 2020, she told WIRED that tech to help with eating disorders is “here to stay” but wouldn’t replace all human-led treatments. \r\n"
					+ "\r\n"
					+ "But without the NEDA helpline staff and volunteers, Tessa is the interactive, accessible tool left in its place—if and when access is restored. When asked what direct resources will remain available through NEDA, Thompson cites an incoming website with more content and resources, along with in-person events. She also says NEDA will direct people to the Crisis Text Line, a nonprofit that connects people to resources for a wide range of mental health issues, like eating disorders, anxiety, and more. " , categories.get(3) , comments.subList(0, 0), authors.get(1), "https://media.wired.com/photos/647691970124a91cee27d944/16:9/w_120,c_limit/Eating-Disorder-Help-Chatbot-Business-1398764138.jpg 120w, https://media.wired.com/photos/647691970124a91cee27d944/16:9/w_240,c_limit/Eating-Disorder-Help-Chatbot-Business-1398764138.jpg 240w, https://media.wired.com/photos/647691970124a91cee27d944/16:9/w_320,c_limit/Eating-Disorder-Help-Chatbot-Business-1398764138.jpg 320w, https://media.wired.com/photos/647691970124a91cee27d944/16:9/w_640,c_limit/Eating-Disorder-Help-Chatbot-Business-1398764138.jpg 640w, https://media.wired.com/photos/647691970124a91cee27d944/16:9/w_960,c_limit/Eating-Disorder-Help-Chatbot-Business-1398764138.jpg 960w");
			Article art10 = new Article("Argentina raises rates again", 2022, 3.9f, 3, "The Central Bank of Argentina (BCRA) raised its benchmark Leliq rate by 600 basis points, to 97%, on May 15. The decision is the fourth rate increase in two months, as policy-makers continue their struggle against inflation and currency depreciation. Since the start of 2022, the BCRA has nearly tripled the Leliq rate from 38%. In 2023, it has increased by 22 percentage points. The decision follows the April inflation reading, which put year-on-year inflation at 108.8%, up from 104.3% in March" , categories.get(2), comments.subList(1, 1), authors.get(0), "https://www.centralbanking.com/sites/default/files/styles/landscape_750_463/public/import/IMG/768/356768/central-bank-of-argentina.jpeg?h=c41c9945&itok=M9oEptMS");
			
			articleRepository.save(art1);
			articleRepository.save(art2);
			articleRepository.save(art3);
			articleRepository.save(art4);
			articleRepository.save(art5);
			articleRepository.save(art6);
			articleRepository.save(art7);
			articleRepository.save(art8);
			articleRepository.save(art9);
			articleRepository.save(art10);
			
			logger.info("All sample data saved!");
			
			

		}
		
		
		
	}
	
	
	@GetMapping("/authors")
	public List<Author> authors(){
		
		return authorRepository.findAll();
	
	}
	
	@PostMapping("/authors/save")
	public Author saveAuthor(@RequestBody Author author) {
		
		Author authorSaved = authorRepository.save(author);
		return authorSaved;
	}
	
	
	
	@GetMapping("/articles")
	public List<Article> books(){
		return articleRepository.findAll();
	}
	
	@PostMapping("/articles/search/author")
	public List<Article> searchArticlesAuthor(@RequestBody ArticlePayload payload){
		
		List<Article> articles = articleRepository.findAllByAuthorId(payload.getAuthorid());	
		return articles;
	}
	
	
	@PostMapping("/articles/search/category")
	public List<Article> searchArticlesCategory(@RequestBody ArticlePayload payload){
		
		List<Article> articles = articleRepository.findAllByCategoryId(payload.getCategoryid());	
		return articles;
	}
	
	@GetMapping("/articles/search/name")
	public List<Article> searchArticlesName(@RequestBody ArticlePayload payload){
		
		List<Article> articles = articleRepository.findByTitleContainsIgnoreCase(payload.getContent());	
		return articles;
	}
	
	@GetMapping("/categories")
	public List<Category> categories(){
		return categoryRepository.findAll();
	}
	
	@PostMapping("/categories/save")
	public Category savePublisher(@RequestBody Category publisher) {
		
		Category catSaved = categoryRepository.save(publisher);
		return catSaved;
	}

	@PostMapping("/articles/comments/get")
	public List<Comment> searchArticlesComments(@RequestBody ArticlePayload payload){
		
	    Article article = articleRepository.findArticleById(payload.getArticleid());
	    
	    List<Comment> comments = article.getComments();
	    return comments;
	    //List<String> commentIds = articleRepository.findCommentIdsByArticleId(article.getId());
	    //logger.info(commentIds.get(0) );
	    //List<Comment> comments = commentRepository.findAllById(commentIds);
	    //return commentIds;
	}

	@PostMapping("/articles/getbyid")
	public Article getArticleById(@RequestBody ArticlePayload payload){
		
	    Article article = articleRepository.findArticleById(payload.getArticleid());
	    
	    
	    return article;
	    //List<String> commentIds = articleRepository.findCommentIdsByArticleId(article.getId());
	    //logger.info(commentIds.get(0) );
	    //List<Comment> comments = commentRepository.findAllById(commentIds);
	    //return commentIds;
	}

	@PostMapping("/articles/comments/post")
	public Article addCommentByArticle(@RequestBody ArticlePayload payload){
		
		Article article = articleRepository.findArticleById(payload.getArticleid());
	    Comment comment = new Comment(payload.getContent(), payload.getTitle());
	    commentRepository.save(comment);
	    article.getComments().add(comment);
	    articleRepository.save(article);
		article = articleRepository.findArticleById(payload.getArticleid());
	    return article;	    
	}
	
	@PostMapping("/articles/rating/post")
	public Article addRating(@RequestBody ArticlePayload payload){
		
		float my_rating = payload.getRating();
		Article article = articleRepository.findArticleById(payload.getArticleid());
		article.setPeople_rated(article.getPeople_rated()+1);
		article.setRating((my_rating+article.getRating()*(article.getPeople_rated()-1))/article.getPeople_rated());
		articleRepository.save(article);
		article = articleRepository.findArticleById(payload.getArticleid());
		return article;	    
	}

}
