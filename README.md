# java8-test

## Reference

http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
http://www.oracle.com/technetwork/articles/java/architect-streams-pt2-2227132.html

## Method reference

기존에 Object reference를 이용해서 객체를 이리저리 주고받았던 것 처럼 자바 8에서는 File::isHidden을 이용해서 Method reference를 만들어 전달할 수 있게 되었다.

old
<pre>
    File[] hiddenFiles = new File(.).listFiles(new FileFilter() {
        public boolean accept(File file) {
            return file:isHidden();
        }
   });
</pre>

java 8
<pre>
    File[] hiddenFiles = new File(.).listFiles(File::isHidden);
</pre>

## Lamda: anonymous functions (page48)

(int x) -> x + 1, 즉, x라는 인수를 호출하면 x + 1을 반환'
