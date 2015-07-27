# Yushing Twitter-Bot

An otherworldly entity that [tweets][1] on behalf of the [Eversleeping Zenmaster Yu-Shing][2].
Can be customized to tweet other stuff at defined intervals, but will require blood rituals and sacrifice and complete submission to Yog-Sothoth.

[1]: https://twitter.com/ZMYuShing
[2]: http://yushing.herokuapp.com/

## Usage

Requires a ```profiles.clj``` like:
```clojure
{:dev  {:env {:app-consumer-key "something"
              :app-consumer-secret "something"
              :user-access-token "something"
              :user-access-secret "something"}}}
```
at the root.

## License

Copyright © 2015 Divyansh Prakash

Distributed under the Eclipse Public License either version 1.0 or any later version.
