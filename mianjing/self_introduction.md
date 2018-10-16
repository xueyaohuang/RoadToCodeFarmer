# About myself

I'm currently a student at master program in computer science at UChicago. As you can see on my resume, 
I have a Electrical Engineering background in both undergraduate and graduate school. But I changed my career to computer
science because I have a huge interest in software engineering. So, over the past year at the UChicago,
I deepened my knowledge on algorithms, data structures and software development.I also empowered my skills in data analytics
during my internship.

And I had a chance to get to know apfolio from my friend Nella, and I get very interested in the company's business and product, for PROPERTY MANAGEMENT, I'm hoping to join the team and get to know more about the engineering practice. 

and mission: To make farming more efficient and profitable, while preserving and enhancing the value of farmland. I'm hoping to join the team and get to know more about the engineering practice. 

of delegate duties to employees  
track inventory  
help farmers to predict revenue and yield. I'm hoping to join the team and get to know more about the engineering practice. 

I have a solid foundation in algorithms, data structures and software development. During my master's degree, I have had many courses that will be helpful, such as Databases, Cloud Computing, Big Data and Advanced Data Analytics. I am familiar with big data development, application and demonstrated my skills in building data analytics platform during my internship.

==============================================================================================================================  
I am currently doing my Master's degree in computer science at University of Chicago. And I will graduate this December.  

I have a solid foundation in algorithms, data structures and web development, including cloud development on AWS.  

I also have experiences in big data from my internship at NetEase as a software developer, where I contributed to the game recommender system.  

Furthermore, I have a passion for learning new cutting-edge technologies of software engineering.  

I am extremely interested in a career opportunity at tiger graph. I hope to contribute to the world's fastest real-time Graph Analytics platform.

==============================================================================================================================

* 3 publications  
Face Classification using Electronic Synapse, published by Nature Communications, May 2017  
IOP science, nanotechnology.  
one conference papaer: international memory worshop  



# Projects

* 最有挑战的项目

During my internship, I tried different models for the game store recommender system.  
The training data set i used is very large, and the models has tons of parameters, so i often encounter out of memory problems
I did some research on memory management and tried many approaches to solve this  
1. reduce intermediate variable during data processing, this imporoves a little.
2. python garbage collection, delete some already used variable manully, and call the garbage collection function. This does not work well.  
3. Then I traced the memory usage and found that during the model prediction stage, the memory increased rapidlly. I need to reduce the testing data in one batch.
   So I set the training and prediction apart. I first train the model and save the it. Then I split the testing data into multiple chunks and predict one by one. It solved the out of memory problem.
   
why not spark, b/c spark doesn't has that built-in algorithm (xgboost)


* 网易实习项目

NetEase Games department in user experience team 
The team is responsible for building recommender systems and data mining works.

recommender systems for game store (Terminator 2: Judgment Day, adapted from the movie, (starred by Arnold Alois Schwarzenegger))
mall, clothes, Champion, skin, decoration, game props

(recommendation machine learning algorithms, lots of work on feature engineering, which can take up to 80% of the all the work  
simplify this feature engineering process, use collaborative filtering, only need user/item interactive data  
e.g. rating, click history, buy history, try on history)

数据处理：  
1. user data logs store in hadoop file systems  
2. build the data processing pipeline using pyspark sql, cache the data as spark dataframe  
3. train the recommender model, als = ALS(rank=16, maxIter=5, seed=0,implicitPrefs=True)  
4. get predict data and item， write recommended items to redis cluster  
3. if i do off-line test, try different algorithms  
   use hive to get useful data, do some data processing using python scripts. and feed data into recommendation model
   off-line test: 1 month user data, predict the purchase behivour next day.

circuit breaker and monitor:
the recommender program is called by scheduler, just in case it's killed, we should give some default recommendations.  
Collaborated with service performance team

爬虫 crawler：
user's reviews and comments on steam
有能人改变了scrapy的队列调度（change the scrapy queue），将起始的网址从start_urls里分离出来，改为从redis读取，多个客户端可以同时读取同一个redis，从而实现了分布式的爬虫。就算在同一台电脑上，也可以多进程的运行爬虫，在大规模抓取的过程中非常有效。




算法，算法包： 
spark python api:  
pyspark machine learning package  
[recommendation module](http://spark.apache.org/docs/latest/api/python/pyspark.ml.html#module-pyspark.ml.recommendation)  
SVD++(Singular-value decomposition)  
ALS(alternative least square) for matrix decomposition.  
implicit feedback  

user rating matrix, element in the matrix corresponds to a user-item-rating, blank means no rating  
use ALS to get the user/item factor matrix, and predict the blank value.  
sort the unrated item of a user, output top 5, that's the recommendation.  

* Gigadevice工作项目

design Nand flash memory, test team will test lots of chips from the foundry.  
test data are stored on hadoop, I use hive sql to retrieve test data  
property: endurance, data retention, thredshold voltage, on state current and many others  
Analyzed test data, the data distribution  
to guide the chip design.

crc: A CRC is an error-detecting code commonly used in digital networks and storage devices to detect accidental changes to raw data. The CRC Component provides 8-, 16- and 32-bit functions to drive data through the CRC hardware, which returns the remainder after dividing the input data stream by a pre-defined generator polynomial (defined by the standard). 


* 硕士研究

I worked as a research assistant on Neuromorphic computing Hardware and emerging memory technologies.  
I used electronic synapses (memristor) to build a multi-layer perceptron, and utilized the neural network to classify grey scale images.  
The synaptic cells allowed the energy consumption to be one thousandths of that of Intel Xeon Phi processor with off-chip memory.  
The neural network combined computing and memory in one device.  

memory: use conductance of memristor as weights of the NN, memristor will memorize the conductance after training.  
computing: u have a voltage on the memristor array, (voltage can simulate binary input), the output current is the computed result.  

u add different voltage on the memristor array, the conductance trainable.

bridge the gap between access speed of main memory (10ns) and flash (100K ns), 
through the design of Resistive Switching Random Access Memory.

# 云计算项目
talk about my Genomics Analysis Web Service proj, capstone proj of cloud computing course
Developed a software as a service on amazon AWS. It's about genomics annotation application.  
genomics annotation: a long sequence of gene(DNA) data, the application is used to annotate each segment of the gene(DNA) data. This piece of gene control the color of eye. 

genomics annotator and web application.  

genomics annotator: I just call the apis, and it will run annotation.  

web application: users submit a genomics file on the web, genomics annotator will analysis the file and generate a result. Users can then download the result. 
Integrated external SaaS providers, like Globus Auth for user identification, Stripe for managing subscription and
billing functions.  

This proj is developed using python and flask framework.
Inorder to decouple back-end services, I use Simple Notification Service (SNS) and Simple Queue Service (SQS) as a message broker.  
The number of annotation requests may change over time, so I achieved auto scaling using Elastic Load Balance (ELB) and Cloud Watch, increase or decrease the number of instances on AWS.

Implemented fast searching for user jobs with DynamoDB, stored user files using S3. If the annotation result is not downloaded for a long time, it will be archieved on aws glacier. When the user wants to download the result, we can take it back to s3 bucket.



# About the company

With Granular, farmers can craft crop and field plans  
delegate duties to employees  
track inventory  
help farmers to predict revenue and yield   

Granular—a farm software business under the agriculture division of DowDuPont—penned a deal with Planet. Planet is an aptly named company, with some 200 satellites around Earth, watching it and its goings-on all the time. As part of this multimillion-dollar, three-year gig announced Tuesday, Granular will get access to daily images of the globe, and some of Planet's six-year archive of snapshots. Granular can feed the pictures into its agricultural analytics tools, which can, in turn, relay wisdom to farmers.
  
# 为啥选我们公司
  1. "Well, one of the biggest reasons I am interested in Granular is because of Granular’s value and mission. I absolutely agree with Food and Farming are essential for our life for living. And I really like the idea of combination of technology and farming business.   
 I believe that helping farmers and their business with a support of cutting-edge software development and data analytics technology can open up a new future of food industry and more predictable production of food products.  
 And, as far as for me, I like software development as well as data analytics. So, it would be great feeling and also motivating for me - if I can be part of your team, helping farmers with software development.
  
  2. I believe granular has a promising future
  3. my interest perfectly matches the job and technology stack. I like data analytics and building software infrastructures.
  wil not fight with product manager.
  
# 问公司问题

"can I ask the whole process of helping farmers? I mean what's the process of getting farming data and how Granular provide an information for farming business?"

"well, i'm very interested in the farming business and its related technology. But is there any program or tranings before university hires can get - before dive into the work?"

Planet.  
Planet has around 200 satellites around Earth, watching it and its goings-on all the time.  
Granular will get access to daily images of the globe, and some of Planet's six-year archive of snapshots. Granular can feed the pictures into its agricultural analytics tools, which can, in turn, relay wisdom to farmers.




  1. 对小公司：Sure, as you know, Tigrt Graph has started its busienss a few years ago,
     so - i believe it has a lot of opportunities in the market place. But, i want
     to ask you what's the comapny's short term goal in its engineering practice
     for the next few years? For example can we provide service in self-driving? I believe 
     it's a huge market.
     
     Sure, as you know, there are some other companies which provide cloud computing services, such as amazon, microsoft, vmware, ect. i believe it has a lot of opportunities in the market place. But, i want to ask you what advantage do we have over the other companies? what's the comapny's short term goal for the next few years?
     
   oh, that's very good
   i like it
     
   Actually I have two more questions that I wanted to ask
     
  2. I want to ask you about service now's career development for the software engineers. for example, are there any
  training sessions or workshops that incoming software engineers can learn more about emerging technologies?
  
  that's great
  
  many other competiors google, linkedin has graph database, could you introduce tigrtgraphs advantages and disadvantages over 
  the other companies?
  
  3. lastly, I want to ask about your experience at service now. I'm sure you have a lot of experience 
     in other companies as well. But, I wanna know what is the most exciting part in working at service now compared to
     other companies.
     
==============================================================================================================================
  2. Can you talk more about the day-to-day responsibilities of this job?
  3. What do you think are the most important qualities for me to excel in this job? 
  4. Do you have any Training program to help new members of the team get on board?
  
  
  1. could you plese introduce the engineering team. I noticed that there are many famous Scientists, professors in machine learning and AI research at petuum. But what about the software engineering team.
  
  
  
  
  2. I want to ask about your experience at appfollio. I'm sure you have a lot of experience 
     in other companies as well. But, I wanna know what is the most exciting part in working at service now compared to
     other companies.
  
  2. introduce the software engineering team, for example, what's the scale of the team, what technology stack u use, something like that.

  
  
  
