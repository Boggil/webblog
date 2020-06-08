package kr.hs.dgsw.webblog.Protocol;

import lombok.Data;

@Data // @Getter @Setter @EqualsAndHashCode @AllArgsConstructor 을 포함한 Lombok에서 제공하는 필드와 관련된 모든 코드를 생성함
public class ResponseFormat {
  private int code;
  private String desc;
  private Object data;

  public ResponseFormat(ResponseType rt, Object data, Object option) {
    this.code = rt.code();
    this.desc = option instanceof Long || option instanceof String 
      ? String.format(rt.desc(), option)
      : rt.desc();
    this.data = data;
  }

  public ResponseFormat(ResponseType rt, Object data) {
    this(rt, data, null);
  }
}