##Herring Agent
Herring Project의 Log Collector 역할을 맡은 프로그램.
유스케이스 "로그 스트리밍", "데이터 소스 등록"과 밀접한 연관성을 갖는다.

##Architecture
![Agent Architecture](http://img405.imageshack.us/img405/8197/20130603101904.png)

회색 부분은 외부 라이브러리를 사용한 부분이고, 흰색 부분은 직접 구현 한 부분이다. 아직 Sender 부분이 제대로 구현되어 있지 않다.


##config.xml
```
<agent>
    <configuration>
        <watcher>
            <type>polling</type>
            <delay>200</delay>
            <target>/Users/hyunje/IdeaProjects/Herring Agent/watching</target>
        </watcher>
        <processor>
            <type>iislogparser</type>
            <rowdelimiter>::herring_row::</rowdelimiter>
            <columndelimiter>::herring_column::</columndelimiter>
            <datadelimiter>::herring_data::</datadelimiter>
        </processor>
        <sender>
            <target>
                <host>61.43.139.117</host>
                <port>8989</port>
            </target>
        </sender>
    </configuration>
</agent>
```
와 같은 형태의 설정 파일이며,
processor > type은 Log Parsing에 사용될 파서의 클래스 이름을 적어주면 된다.
