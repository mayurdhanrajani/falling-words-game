# Falling Words

## Info

- Falling Words is a small language game where the user will see a word in English at the bottom
  and its translation in Spanish will fall down. Here the user has to choose whether the translated
  word which is falling down is correct or not. The game consists of only 2 screens:
  1. Home screen
  2. Game screen

## Home screen

- On the Home screen, there is only the app name and the Play button. Clicking on the Play button
  will redirect the user to the Game screen and they can start playing the game.
- The data will be fetched from API and will be stored in the database. Once the data is stored,
  the app can work offline after that.
- If there is no internet connection for the first time, upon clicking the Play button will show a
  dialog to turn on the internet and try after some time.

## Game screen

- At the top left, there is a word count which will show many words that have been shown till now.
- At the top right, there is a score count which will increment when the user chooses the option
  correctly.
- At the top center, there is a timer that will show the user how much time they have to
  choose the option correctly.
- At the bottom center, there is a word that is in English, and from the top center, a word will
  fall down which is in Spanish.
- At the bottom, there are two options - on the left side is the incorrect button which should be
  clicked if the translated word is incorrect and on the right side is the correct button which should
  be clicked if the translated word is correct.

## Rules

- Here, the game will consist of 10 words only.
- For each word, the user will have 5 seconds to choose if the translated word is correct or not.
- If the word is translated correctly, click on the correct button and if the word is not
  translated correctly, click on the incorrect button. Doing so will increment the score.
- If the user is not able to decide within the time limit, the score will stay as it is and there
  will be no decrement in the score.
- Clicking on the correct or incorrect button will show the next word.
- If the user is unable to choose the option within the time limit, the next word will be shown
  automatically after 5 seconds.
- After all the 10 words are shown or 50 seconds have passed, a dialog will be shown informing the
  user that the game is completed and they should click on the OK button to play the game again.

## Solution Design

- The app has Single Activity MVVM architecture which is recommended by Google and should be used
  if one has no idea where to start. Here the code is divided into 3 packages:
  1. View - consists of fragments
  2. ViewModel - consists of ViewModel
  3. Model - consists of Model, DataModel, API, and Repository.
- The entire app is connected with dependencies using the Hilt DI framework. Again, it is
  recommended by the Google and has great developer support along with ease of use and
  compile-time checks.
- For networking, I have used Retrofit which is the most popular networking library, easy to use,
  supports all the functionalities and is compatible with Android components.
- For the database, I have used Room which is another library by Google same as Retrofit but for
  database operations. The library is compatible with all other Android Jetpack components and is
  easy to use.
- Besides that, I have used different modules for different use cases of providing dependencies in
  the app.
- For navigation, I have used the Jetpack navigation library which helps navigate the fragments very
  easily and reduces a lot of manual handling of state management and transitions.
- For referencing view, I have used ViewBinding which is faster and much safer compared to
  other alternatives such as DataBinding, findViewById, and Kotlin synthetics.
- Finally, for the UI part, since it takes a lot of time to design a good UI, I went with the
  black-and-white approach. The UI will change once the dark mode is changed in the system settings.