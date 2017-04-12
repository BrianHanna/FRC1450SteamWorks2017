def emptyCallBack(pram1, pram2):
    return;

import cv2
import numpy as np

cap = cv2.VideoCapture(0)
# create trackbars for color change
#cv2.createTrackbar('HMin','image',0,255,nothing)
cv2.namedWindow('image')
cv2.createTrackbar('HMin','image',0,255,emptyCallBack)
cv2.createTrackbar('HMax','image',0,255,emptyCallBack)
cv2.createTrackbar('SMin','image',0,255,emptyCallBack)
cv2.createTrackbar('SMax','image',0,255,emptyCallBack)
cv2.createTrackbar('VMin','image',0,255,emptyCallBack)
cv2.createTrackbar('VMax','image',0,255,emptyCallBack)

while(1):

    # Take each frame
    _, frame = cap.read()

    # Convert BGR to HSV
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
# get current positions of four trackbars
    hMin = cv2.getTrackbarPos('HMin','image')
    hMax = cv2.getTrackbarPos('HMax','image')
    sMin = cv2.getTrackbarPos('SMin','image')
    sMax = cv2.getTrackbarPos('SMax','image')
    vMin = cv2.getTrackbarPos('VMin','image')
    vMax = cv2.getTrackbarPos('VMax','image')
    
    # define range of blue color in HSV
    lower_blue = np.array([110,50,50])
    upper_blue = np.array([130,255,255])
    lower_green = np.array([hMin,sMin,vMin])
    upper_green = np.array([hMax,sMax,vMax])

    # Threshold the HSV image to get only blue colors
    mask = cv2.inRange(hsv, lower_green, upper_green)

    # Bitwise-AND mask and original image
    res = cv2.bitwise_and(frame,frame, mask= mask)

    cv2.imshow('frame',frame)
    cv2.imshow('mask',mask)
    cv2.imshow('res',res)
    cv2.imwrite('filteredImage.png',mask)
    k = cv2.waitKey(5) & 0xFF
    if k == 27:
        break

cv2.destroyAllWindows()
