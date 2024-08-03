# Memento

[Memento](https://en.wikipedia.org/wiki/Memento_pattern) interfaces for Java. 

```xml
<dependency>
    <groupId>org.open-structures</groupId>
    <artifactId>memento</artifactId>
    <version>1.0.0</version>
</dependency>
```

A set of interfaces to save the state of the object so that it can be restored later.

Here's an example of the `Message` which state can be "saved":

    public class Message implements Restorable<Message.MessageSnapshot> {
        private StringBuilder stringBuilder = new StringBuilder();
    
        public void addLine(String l){
            stringBuilder.append(l).append("\n");
        }
    
        public String getContents(){
            return stringBuilder.toString();
        }
    
        @Override
        public MessageSnapshot getState() {
            return new MessageSnapshot(getContents());
        }
    
        @Override
        public void restore(MessageSnapshot state) {
            stringBuilder = new StringBuilder(state.letterContents);
        }
    
        public static class MessageSnapshot implements Memento{
            private final String letterContents;
    
            public MessageSnapshot(String letterContents) {
                this.letterContents = letterContents;
            }
        }
    }

Here's how we can save and restore the message to its previous state:

    Message msg = new Message();
    msg.addLine("What is Pi?");
    Message.MessageSnapshot saved = msg.getState();
    msg.addLine("Three. More or less.");
    msg.restore(saved);
    msg.getContents(); // What is Pi?
    
    