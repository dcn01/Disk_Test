#Disk Test (Version 3.0) 
This Android APP is use to test your device's SD/eMMC.   
Developed by Aningsk, and comply with Apache License Version 2.0

##Description
Well actually the APP just maltreat your device. Yes, it is.  
This APP can calculate the speed of read/write in SD/eMMC, and save the result in a file.
User can select where they want to take test on (Internal or External Disk). It also show 
some other information such as RAM size, disk size and so on.  
As a whole test will spend too long time. In most case I don't take full test.
If you want to take different quantity test, please change the QUANTITY or testsize in TestService.java 
and make sure that your device has enough big disk. You can change the buffer size and repeat times 
used by the APP to run the test.   
When the APP takes a test, it will create TestFile.txt with random data, and copy it as 
TempFile.txt . APP can check the CRC32 of these two file are the same value or not, and get the 
speed of read/write. And repeat 5 times (you can change it). Test result content is saved in 
TestResult.txt user can see it by clicking "Result" button. This file also saved the buffer size 
and test start/end time. You also can refresh the result by clicking "Partitions"/"Result" button.   
If you stop one test manually, please make sure that the TestResult.txt had been written with 
"End Time: ...", that showes the test is real end. And then you can take other test safely.

I use ADT(eclipse) to develop this project. I cannot get Android Studio.  
And run it on Android-4.4.2 (I don't test it on other Android versions.), 
I considered much older Android such as 4.2.2, but Android-4.4 is my target device.

* Android Developer Tools  
    Build:v22.6.2-1085508  

That is very very difficult to get develop tools or support libraries, since Google had been **GFWed**... 
I'm a Chinese newbie programmer without any VPN @\_@

***
##Version Mark 
* v3.0 (2016-03-24) Major Upgrade   
    Yes! Now it is enough cool for the name "v3.0"!  
    User can select the buffer size and test times on the UI. And if something are important APP would 
    tell them to user, such as disk is not big enough. I also change the content of TestResult.txt, 
    this file will record the buffer size and start/end time.  
* v2.10 (2016-03-22)  
    OK I had realized the function that should be in v3.0 which I predicted. But I don't use "v3.0" 
    because it's not so cool for the "3.0". I don't change v2.9 versionCode just change the versionName 
    to v2.10 so they are twins. 
    This version APP will use 1KB buffer size to read/write a file. So it will be much slower than 
    previous version.  
    **NOTICE**: If you want take the test as fast as your device can, use v2.9 please. If you want test 
    with invariable buffer size, use v2.10 please.  
* v2.9 (2016-03-22) Major Upgrade   
    v1.6.1 have a BUG: It cannot test with large file. Now I had fixed it, and change a lot of code. 
    Yes, it's v2.9 near v3.0  
    First of all, I seperate the test file as different units(KB and MB) in order to fix the BUG. 
    And I use CRC32 instead of MD5, CRC32 is more faster and lighter. Then I adjust much code for more 
    elegant. That always be consistent.
    In fact, I forked v2.0.2 from v1.6.1 to fix the issue, but I didn't release it. Finally I merge it 
    into v2.0.1 and go on developing the project.  
* v2.0.1 (2016-03-18)   
    Fixed sometimes cannot show the highest order number of RAM size.  
* v2.0 (2016-03-14)  
    Add cross test function. I don't like this function. But leader likes it.   
    I think that cross test is not necessary while we have the eMMC and SD card 
    read/write tests.  
    Well I realized the function, just as I can.  
    The result showed by the APP in cross test is different:  
    I don't take care of average speed of this funtion; but I mark the direction 
    of read/write - E->I means the file was created on external disk and copy to 
    internal disk. I->E is similar.  
    I also don't show DiskSize in crose test.  
    I suggest you'd better use the v1.6.1 though it's old with more bugs probably.  
* v1.6.1 (2016-01-25)   
    Fixed some issue when device without SD card:   
    1 ShowView don't show correct information. FIXED   
    2 RadioGroup can select one that shouldn't be selected. FIXED  
* v1.6 (2016-01-22)   
    Support that take test on internal disk or external disk.
* v1.5.1 (2016-01-22)   
    It can show the TestResult.txt on UI.   
    Unitize some values by DiskSizeApplication.
* v1.5 (2016-01-21)   
    Now the APP can read/write in internal disk, as the test files are in the APP private path.  
    So it does not need uid.system. 
* v1.4 (2016-01-20)   
    If something is getting wrong such as IOEception, user can know that.   
    DiskSize means /data size, not /sdcard. If necessary I can change it.   
    All file created by the APP will be saved in "DiskTest" folder.
* v1.3.3 (2016-01-20)
    BUG:   
    If user stops a running TestService, the last average speed will get wrong.   
    Fixed.
* v1.3.2 (2016-01-19)   
    Decimal format as 0.000000 in the TestResult.txt   
    Adjust some strings.
* v1.3.1 (2016-01-19)   
    Support Simplified Chinese Language.  
* v1.3 (2016-01-19)   
    The APP can show RAM size, eMMC size and partitions information.
* v1.2 (2016-01-18)   
    The read/write speed is more accurate than before!  
    If there is some fail in test, APP can tell us.
* v1.1 (2016-01-18)   
    Use MD5 checksum, and change read/write API to FileReader/FileWriter.  
    The data written in file is random.
* v1.0 (2016-01-15)  
    I use my old project to init the git repo.  
    Some functions were too stupid, I'll change them.

***
## License

    Copyright 2016 Aningsk

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
