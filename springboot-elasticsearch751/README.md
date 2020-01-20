## Elasticsearch7.5.1 docker 安装
```yaml
version: '2.2'
services:
  cerebro:
    image: lmenezes/cerebro:0.8.5
    container_name: cerebro
    ports:
      - "9000:9000"
    command:
      - -Dhosts.0.host=http://elasticsearch:9200
    networks:
      - es7net
  kibana:
    image: docker.elastic.co/kibana/kibana:7.5.1
    container_name: kibana7
    environment:
      - I18N_LOCALE=zh-CN
      - XPACK_GRAPH_ENABLED=true
      - TIMELION_ENABLED=true
      - XPACK_MONITORING_COLLECTION_ENABLED="true"
    ports:
      - "5601:5601"
    networks:
      - es7net
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.5.1
    container_name: es7_01
    environment:
      - cluster.name=geektime
      - node.name=es7_01
      - bootstrap.memory_lock=true
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
      - discovery.seed_hosts=es7_01,es7_02
      - cluster.initial_master_nodes=es7_01,es7_02
    ulimits:
      memlock:
        soft: -1
        hard: -1
    volumes:
      - es7data1:/usr/share/elasticsearch/data
    ports:
      - 9200:9200
    networks:
      - es7net
  elasticsearch2:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.5.1
    container_name: es7_02
    environment:
      - cluster.name=geektime
      - node.name=es7_02
      - bootstrap.memory_lock=true
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
      - discovery.seed_hosts=es7_01,es7_02
      - cluster.initial_master_nodes=es7_01,es7_02
    ulimits:
      memlock:
        soft: -1
        hard: -1
    volumes:
      - es7data2:/usr/share/elasticsearch/data
    networks:
      - es7net


volumes:
  es7data1:
    driver: local
  es7data2:
    driver: local

networks:
  es7net:
    driver: bridge
```
## 索引创建和查询
```json
GET /_cat/plugins

#ik_max_word
#ik_smart
#hanlp: hanlp默认分词
#hanlp_standard: 标准分词
#hanlp_index: 索引分词
#hanlp_nlp: NLP分词
#hanlp_n_short: N-最短路分词
#hanlp_dijkstra: 最短路分词
#hanlp_crf: CRF分词（在hanlp 1.6.6已开始废弃）
#hanlp_speed: 极速词典分词

POST _analyze
{
  "analyzer": "hanlp_standard",
  "text": ["剑桥分析公司多位高管对卧底记者说，他们确保了唐纳德·特朗普在总统大选中获胜"]

}

# 查看索引模板
GET /_cat/templates

# 创建索引
PUT /security-evaluation-v2
{
    "settings" : {
      "analysis": {
        "analyzer": {
          
          "hanlp": {
            "tokenizer": "hanlp_standard"
          },
          "pinyin": {
            "tokenizer": "pinyin"
          }
        }
      }
    }
}
# 查看索引
GET /security-evaluation-v2/_search
{
  "query": {
    "match": {
      "title.pinyin": "biaoti"
    }
  }
}

GET /security-evaluation-v2/_search
{
  "query": {
    "bool": {
      "must": [
        {"match": {
          "type": "3"
        }}
      ], 
      "should": [
        {
          "match": { 
            "title": "zhangsan"
          }
        },
        {
          "match": {
            "name": "zhangsan"
          }
        },
        {
          "match": {
            "content": "zhangsan"
          }
        },
        {
          "match": {
            "title.pinyin": "zhangsan"
          }
        },
        {
          "match": {
            "name.pinyin": "zhangsan"
          }
        }
      ]
    }
  }, 
  "from": 0,
  "size": 20
}

# 删除索引
DELETE /security-evaluation-v1

GET /security-evaluation-v2

# 查看该索引下字段是如何分词的
GET /security-evaluation-v2/_analyze
{
  "field": "name.pinyin", 
  "text":"张三2"
}

#创建mapping
PUT /security-evaluation-v2/_mapping
{
    "properties" : {
        "createTime" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "fileId" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "id" : {
          "type" : "long"
        },
        "name" : {
          "type" : "text",
          "analyzer": "hanlp",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            },
            "pinyin" : {
              "type" : "text",
              "analyzer" : "pinyin"
            }
          }
        },
        "content" : {
          "type" : "text",
          "analyzer": "hanlp",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "status" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "title" : {
          "type" : "text",
          "analyzer": "hanlp",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            },
            "pinyin" : {
              "type" : "text",
              "analyzer" : "pinyin"
            }
          }
        },
        "type" : {
          "type" : "long"
        }
    }
  }
```