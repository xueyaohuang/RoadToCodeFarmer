# About myself

I'm currently a student at master program in computer science at UChicago. As you can see on my resume, 
I have a Electrical Engineering background in both undergraduate and graduate school. But I changed my career to computer
science because I have a huge interest in software engineering. So, over the past year at the UChicago,
I deepened my knowledge on algorithms, data structures and software development.I also empowered my skills in data analytics
during my internship.

And I had a chance to get to know servicenow from its website, and I get very interested in the company's business of cloud computing for enterprise. I'm hoping to join the team and get to know more about the engineering practice. 



I have a solid foundation in algorithms, data structures and software development. During my master's degree, I have had many courses that will be helpful, such as Databases, Cloud Computing, Big Data and Advanced Data Analytics. I am familiar with big data development, application and demonstrated my skills in building data analytics platform during my internship.

==============================================================================================================================  
I am currently doing my Master's degree in computer science at University of Chicago. And I will graduate this December.  

I have a solid foundation in algorithms, data structures and web development, including cloud development on AWS.  

I also have experiences in big data from my internship at NetEase as a software developer, where I contributed to the game recommender system.  

Furthermore, I have a passion for learning new cutting-edge technologies of software engineering.  

==============================================================================================================================

* 3 publications  
Face Classification using Electronic Synapse, published by Nature Communications, May 2017  
IOP science, nanotechnology.  
one conference papaer: international memory worshop  



# Projects

* 骄傲的项目

I am proud of the recommender system project I finished at NetEase Inc. during my internship. I built a recommender systems for the game store (Terminator 2: Judgment Day) to give the most likely items that a user may purchase.

I developed a data processing pipeline using Hive and sparkSQL. Then I utilized Spark SVD++ algorithm for the recommender model. For the recommendation results, I put the prediction results to Redis for fast query.

I am proud of this project because of two reasons. First, the old recommendation system uses traditional machine learning algorithms which requires lots of work on feature engineering. It can take up to 80% of the all the work. My approach simplified this feature engineering process by using collaborative filtering, which only need user/item interactive data. Second, the recommendation result is pretty good. It got 7% higher buy rate compared to the recommendation strategy by rules.

* 最有挑战的项目

During my internship, I tried different models for the game store (Terminator 2: Judgment Day) recommender system.
The training data set I used is very large, and the models has tons of parameters so I often encountered out of memory problems. I did some research on memory management and tried many approaches to solve this.
First, I reduced intermediate variable during data processing, this saves a little bit memory. Then I learned python garbage collection, delete some already used variable manually, and call the garbage collection function. This did not work well.
Further more, I traced the memory usage and found that during the model prediction stage, the memory increased rapidly. I need to reduce the testing data in one batch. So I set the training and prediction apart. I first trained the model and saved the it. Then I split the testing data into multiple chunks and predicted one by one. It solved the out of memory problem.
   
why not spark, b/c spark doesn't has that built-in algorithm (xgboost)


* 网易实习项目

NetEase Games department in user experience team 
The team is responsible for building recommender systems and data mining works.

recommender systems for game store (Terminator 2: Judgment Day, adapted from the movie, (starred by Arnold Alois Schwarzenegger))
mall, clothes, Champion, skin, decoration, game props  
so my recommender systems is to give the most likely  items that a user may purchaesd, maximize the profit.


before i do this, the problem is: recommendation machine learning algorithms, lots of work on feature engineering, which can take up to 80% of the all the work  
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

circuit breaker and monitor: (collabrote with game server platform team)
the recommender program is called by scheduler, just in case it's somehow killed, we should give some default recommendations.  Collaborated with service performance team

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

(utilized many aws tools: S3 and Glacier to store user files and annotated results, build user's profile database using DynamoDB  
Inorder to decouple back-end services, I use Simple Notification Service (SNS) and Simple Queue Service (SQS) as a message broker.  
The number of annotation requests may change over time, so I achieved auto scaling using Elastic Load Balance (ELB) and Cloud Watch, increase or decrease the number of instances on AWS.  
)
genomics annotation: a long sequence of gene(DNA) data, the application is used to annotate each segment of the gene(DNA) data. This piece of gene control the color of eye. 

genomics annotator and web application.  

genomics annotator: I just call the apis, and it will run annotation.  

web application: users submit a genomics file on the web, genomics annotator will analysis the file and generate a result. Users can then download the result. 
Integrated external SaaS providers, like Globus Auth for user identification, Stripe for managing subscription and
billing functions.  

This proj is developed using python and flask framework.
Inorder to decouple back-end services, I use Simple Notification Service (SNS) and Simple Queue Service (SQS) as a message broker.  
web app 
The number of annotation requests may change over time, so I achieved auto scaling using Elastic Load Balance (ELB) and Cloud Watch, increase or decrease the number of instances on AWS.

Implemented fast searching for user jobs with DynamoDB, stored user files using S3. If the annotation result is not downloaded for a long time, it will be archieved on aws glacier. When the user wants to download the result, we can take it back to s3 bucket.

[message queue](https://github.com/xueyaohuang/RoadToCodeFarmer/blob/master/Concepts/%E6%B6%88%E6%81%AF%E9%98%9F%E5%88%97.md)

# didi restful 项目
restful: REpresentational State Transfer. It is design style for distributed hypermedia systems, takes advantage of HTTP when used for Web APIs. resource based bot action based.  
resource is identified by URIS (Uniform Resource Identifier), separate from their representations.
resource representations is transferred between client and server, JSON, XML format.  

[http stuts code](https://www.restapitutorial.com/httpstatuscodes.html)

toy project to practice how to design RESTful API and build the project using Spring Cloud  
microservices (account, dispatch, location...)  

Client side load balancing maintains an algorithm like round robin or zone specific, by which it can invoke instances of calling services. The advantage is s service registry always updates itself; if one instance goes down, it removes it from its registry, so when the client side load balancer talks to the Eureka server, it always updates itself, so there is no manual intervention- unlike server side load balancing- to remove an instance.

round robin time slices (also known as time quanta)[3] are assigned to each process in equal portions and in circular order, handling all processes without priority (also known as cyclic executive). Round-robin scheduling is simple, easy to implement, and starvation-free. 

# personal blog 
Developed a personal blog system with Django  
Utilized Elasticsearch for full-text search and auto-complete  
docker  
docker provide very good reproducibility, a Docker container is guaranteed to be identical on any system that can run Docker. 
  i have developed a personal blog web app. u know, there are many tools or dependencies, such as PostgreSQL for persistent storage, Redis for temporary， Elasticsearch for full-text search and auto-complete
  i package all the software tools in a docker image and upload to my docker repo, 
  when i want to run my blog app on another computer, i just down that docker image, all the configuration is done very easily.

# About the company


# 问公司问题

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
  
  
  
  
  2. I want to ask about your experience at appfolio. I'm sure you have a lot of experience 
     in other companies as well. But, I wanna know what is the most exciting part in working at service now compared to
     other companies.
  
  2. introduce the software engineering team, for example, what's the scale of the team, what technology stack u use, something like that.
  
  I want to ask you about appfolio's career development for the software engineers. pair programming. for example, are there any training sessions or workshops that incoming software engineers can learn more about emerging technologies?

  
  # automation tool? 用了啥，叫啥名字 aws上的automation tool
  test the auto scaling ability of my web application. I need to simulate the situation that millions of  users are visiting my web site simultaneously.
  An open source load testing tool called locust. write Python code with locust to Define user behaviour with, and simulate the situation that millions of  users are visiting my web site simultaneously.
  
  swarm your system with millions of simultaneous users.
  
  # docker, 在哪用的？
  
  docker provide very good reproducibility, a Docker container is guaranteed to be identical on any system that can run Docker. 
  i have developed a personal blog web app. u know, there are many tools or dependencies, such as PostgreSQL for persistent storage, Redis for temporary， Elasticsearch for full-text search and auto-complete
  i package all the software tools in a docker image and upload to my docker repo, 
  when i want to run my blog app on another computer, i just down that docker image, all the configuration is done very easily.
  
  The exact specifications of a container are stored in a Dockerfile. By distributing this file among team members, an organization can guarantee that all images built from the same Dockerfile will function identically. In addition, having an environment that is constant and well-documented makes it easier to keep track of your application and identify problems.
  
  
  
  
  
