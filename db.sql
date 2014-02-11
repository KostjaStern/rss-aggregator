
CREATE SEQUENCE auto_id_rss_feeds;

CREATE TABLE rss_feeds (
    id      integer NOT NULL DEFAULT nextval('auto_id_rss_feeds'),
    name    varchar(50),
    url     varchar(250),
	PRIMARY KEY (id)
);


CREATE SEQUENCE auto_id_rss_news;

CREATE TABLE rss_news (
    id      integer NOT NULL DEFAULT nextval('auto_id_rss_news'),
	feed_id integer,
    title   varchar(250),
    link    varchar(250),
	PRIMARY KEY (id)
);


/*

INSERT INTO rss_feeds (name, url) VALUES
    ('habrahabr', 'http://habrahabr.ru/rss/feed/posts/2dad45b1cde9c116b15bda3e488350f6'),
    ('core-java', 'http://www.javaworld.com/category/core-java/index.rss');

*/