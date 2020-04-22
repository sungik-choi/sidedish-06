DROP TABLE IF EXISTS overview;
DROP TABLE IF EXISTS delivery;
DROP TABLE IF EXISTS badge;
# DROP TABLE IF EXISTS thumb_image;
# DROP TABLE IF EXISTS detail_section;
# DROP TABLE IF EXISTS banchan;

CREATE TABLE overview
(
    detail_hash VARCHAR(64) PRIMARY KEY,
    image       VARCHAR(256),
    alt         VARCHAR(256),
    title       VARCHAR(256),
    description VARCHAR(256),
    n_price     VARCHAR(256),
    s_price     VARCHAR(256)
);

CREATE TABLE delivery
(
    detail_hash VARCHAR(64) REFERENCES overview (detail_hash) ON UPDATE CASCADE ON DELETE CASCADE,
    type        VARCHAR(64)
);

CREATE TABLE badge
(
    detail_hash VARCHAR(64) REFERENCES overview (detail_hash) ON UPDATE CASCADE ON DELETE CASCADE,
    event       VARCHAR(32)
);

# CREATE TABLE banchan
# (
#     # 메인 JSON
#     food_type      VARCHAR(64),  # fk
#     hash           VARCHAR(32) PRIMARY KEY,
#     image          VARCHAR(128),
#     alt            VARCHAR(64),
# #     delivery_type VARCHAR(128),         # Json Array, fk
#     title          VARCHAR(128),
#     description    VARCHAR(128),
#     n_price        VARCHAR(32),
#     s_price        VARCHAR(32),
# #     badge VARCHAR(32),                  # Json Array, fk
#     # 상세정보 JSON
#     top_image      VARCHAR(128),
#     thumb_image    VARCHAR(128), # Json Array, fk
#     point          VARCHAR(128),
#     delivery_info  VARCHAR(128),
#     delivery_fee   VARCHAR(128),
#     detail_section VARCHAR(128)  # fk
# );


# CREATE TABLE thumb_image
# (
#     thumb_image VARCHAR(128),
#     hash_id     INT REFERENCES banchan (hash) ON UPDATE CASCADE ON DELETE CASCADE
# );
#
# CREATE TABLE detail_section
# (
#     detail_section VARCHAR(128),
#     hash_id        INT REFERENCES banchan (hash) ON UPDATE CASCADE ON DELETE CASCADE
# );