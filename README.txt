ASSUMPTIONS MADE

For the final part of the exercise (concerning nodes who’s status may end up being unknown) I have taken the passage to mean the following:

- Times on all network nodes are synced within 50ms of each other
- There is therefore 50ms potential degree of error between any times given by two nodes
- This means that if a notification was sent at 1508405807479, and another sent at 1508405807487, we cannot be certain which notification was sent first, as the timings are within range of uncertainty
- I have therefore built my program to make a nodes status UNKNOWN if any two notifications that effect it are sent within 50ms of each other


HOW TO RUN

The main() method of my program lies within readNodes.java

Thus, to run you would type “java ReadNodes ‘sampleTextFileName.txt’”