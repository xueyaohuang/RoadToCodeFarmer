# About myself

I'm currently a graduate cadidate at master program in computer science at UChicago. As you can see on my resume, 
I have a Electrical Engineering background in both undergraduate and graduate school. But I changed my career to computer
science because I have a huge interest in software engineering. So, over the past year at the UChicago,
I deepened my knowledge on algorithms, data structures and software development.I also empowered my skills in data analytics
during my internship.

And I had a chance to get to know Tiger Graph through its website, and I get very interested in the company's business of building a real-time graph analytics platform. I'm hoping to join the team and get to know more about the engineering practice.  

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
2. if i do off-line test, try different algorithms  
   use hive to get useful data, do some data processing using python scripts. and feed data into recommendation model
   off-line test: 1 month user data, predict the purchase behivour next day.



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




# About the company

* Responsibilities  
  1. Design and implement domain-specific query language engine.  
  2. Design and implement architectural innovations to support a cutting-edge, high-performance, highly-available database and analytics stack.  
  3. Formulate and deliver tools that have high impact on the company's daily operation.  
  4. Promote team success and continually invent ways to improve team productivity.  
  5. Write user-level document and code review peers' deliverables.  
  6. Perform unit and end-to-end regression testing of the deliverables.
  
* 问公司问题
  1. Sure, as you know, Tigrt Graph has started its busienss a few years ago,
     so - i believe it has a lot of opportunities in the market place. But, i want
     to ask you what's the comapny's short term goal in its engineering practice
     for the next few years? For example can we provide service in self-driving? I believe 
     it's a huge market.
     
   oh, that's very good
   i like it
     
   Actually I have two more questions that I wanted to ask
     
  2. I want to ask you about TG's career development for the software engineers. for example, are there any
  training sessions or workshops that incoming software engineers can learn more about emerging technologies?
  
  that's great
  
  3. lastly, I want to ask about your experience at TG. I'm sure you have a lot of experience 
     in other companies as well. But, I wanna know what is the most exciting part in working at TG compared to
     other companies.
     
==============================================================================================================================
  2. Can you talk more about the day-to-day responsibilities of this job?
  3. What do you think are the most important qualities for me to excel in this job? 
  4. Do you have any Training program to help new members of the team get on board?
  

  
  
  
