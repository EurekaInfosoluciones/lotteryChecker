# lotteryChecker
The most up to date work is on the branch "refactorScannerFragment"

This is parked since the data feed I was going to use is protected by tokens.
The logic to create the user seems to be pretty easy, but not sure if it is worth going through the pain to workaround this in the device,
or invest a bit more of time and do it in a backend instead.

Backend:
Pros:
1. No update required if the app gets visibility and a new workaround is required.
2. Just one token
3. In the future I could own the data
4. Could use multiple feeds, aka easier to get a 100% up time.
5. Could use same endpoint for both, once and loterias y apuestas
Cons:
1. Need to go back to web development.


Android
Pros:
1. Will take less time to implement.
Cons:
1. Generates "noisy" tokens on the backend, therefore this app could be detected easily
2. Losing focus on what matters renewing tokens that I should not need.


Another possibility is to continue with the serverless approach but without writing tests for the part related to tokens.