package io.pigeon.access;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: person.proto

/**
 * <pre>
 * define address message
 * </pre>
 *
 * Protobuf type {@code Address}
 */
public final class Address extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Address)
        AddressOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Address.newBuilder() to construct.
  private Address(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Address() {
    city_ = "";
    country_ = "";
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new Address();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return PersonOuterClass.internal_static_Address_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 3:
        return internalGetProperties();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return PersonOuterClass.internal_static_Address_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            Address.class, Builder.class);
  }

  public static final int CITY_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile Object city_ = "";
  /**
   * <code>string city = 1;</code>
   * @return The city.
   */
  @Override
  public String getCity() {
    Object ref = city_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      city_ = s;
      return s;
    }
  }
  /**
   * <code>string city = 1;</code>
   * @return The bytes for city.
   */
  @Override
  public com.google.protobuf.ByteString
      getCityBytes() {
    Object ref = city_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      city_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int COUNTRY_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile Object country_ = "";
  /**
   * <code>string country = 2;</code>
   * @return The country.
   */
  @Override
  public String getCountry() {
    Object ref = country_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      country_ = s;
      return s;
    }
  }
  /**
   * <code>string country = 2;</code>
   * @return The bytes for country.
   */
  @Override
  public com.google.protobuf.ByteString
      getCountryBytes() {
    Object ref = country_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      country_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PROPERTIES_FIELD_NUMBER = 3;
  private static final class PropertiesDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        String, String> defaultEntry =
            com.google.protobuf.MapEntry
            .<String, String>newDefaultInstance(
                PersonOuterClass.internal_static_Address_PropertiesEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.STRING,
                "");
  }
  @SuppressWarnings("serial")
  private com.google.protobuf.MapField<
      String, String> properties_;
  private com.google.protobuf.MapField<String, String>
  internalGetProperties() {
    if (properties_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          PropertiesDefaultEntryHolder.defaultEntry);
    }
    return properties_;
  }
  public int getPropertiesCount() {
    return internalGetProperties().getMap().size();
  }
  /**
   * <code>map&lt;string, string&gt; properties = 3;</code>
   */
  @Override
  public boolean containsProperties(
      String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    return internalGetProperties().getMap().containsKey(key);
  }
  /**
   * Use {@link #getPropertiesMap()} instead.
   */
  @Override
  @Deprecated
  public java.util.Map<String, String> getProperties() {
    return getPropertiesMap();
  }
  /**
   * <code>map&lt;string, string&gt; properties = 3;</code>
   */
  @Override
  public java.util.Map<String, String> getPropertiesMap() {
    return internalGetProperties().getMap();
  }
  /**
   * <code>map&lt;string, string&gt; properties = 3;</code>
   */
  @Override
  public /* nullable */
String getPropertiesOrDefault(
      String key,
      /* nullable */
String defaultValue) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<String, String> map =
        internalGetProperties().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, string&gt; properties = 3;</code>
   */
  @Override
  public String getPropertiesOrThrow(
      String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<String, String> map =
        internalGetProperties().getMap();
    if (!map.containsKey(key)) {
      throw new IllegalArgumentException();
    }
    return map.get(key);
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(city_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, city_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(country_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, country_);
    }
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetProperties(),
        PropertiesDefaultEntryHolder.defaultEntry,
        3);
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(city_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, city_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(country_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, country_);
    }
    for (java.util.Map.Entry<String, String> entry
         : internalGetProperties().getMap().entrySet()) {
      com.google.protobuf.MapEntry<String, String>
      properties__ = PropertiesDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, properties__);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof Address)) {
      return super.equals(obj);
    }
    Address other = (Address) obj;

    if (!getCity()
        .equals(other.getCity())) return false;
    if (!getCountry()
        .equals(other.getCountry())) return false;
    if (!internalGetProperties().equals(
        other.internalGetProperties())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + CITY_FIELD_NUMBER;
    hash = (53 * hash) + getCity().hashCode();
    hash = (37 * hash) + COUNTRY_FIELD_NUMBER;
    hash = (53 * hash) + getCountry().hashCode();
    if (!internalGetProperties().getMap().isEmpty()) {
      hash = (37 * hash) + PROPERTIES_FIELD_NUMBER;
      hash = (53 * hash) + internalGetProperties().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static Address parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Address parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Address parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Address parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Address parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static Address parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static Address parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Address parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static Address parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static Address parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static Address parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static Address parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(Address prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * define address message
   * </pre>
   *
   * Protobuf type {@code Address}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Address)
          AddressOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return PersonOuterClass.internal_static_Address_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetProperties();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 3:
          return internalGetMutableProperties();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PersonOuterClass.internal_static_Address_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Address.class, Builder.class);
    }

    // Construct using Address.newBuilder()
    private Builder() {

    }

    private Builder(
        BuilderParent parent) {
      super(parent);

    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      city_ = "";
      country_ = "";
      internalGetMutableProperties().clear();
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return PersonOuterClass.internal_static_Address_descriptor;
    }

    @Override
    public Address getDefaultInstanceForType() {
      return Address.getDefaultInstance();
    }

    @Override
    public Address build() {
      Address result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public Address buildPartial() {
      Address result = new Address(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(Address result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.city_ = city_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.country_ = country_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.properties_ = internalGetProperties();
        result.properties_.makeImmutable();
      }
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof Address) {
        return mergeFrom((Address)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(Address other) {
      if (other == Address.getDefaultInstance()) return this;
      if (!other.getCity().isEmpty()) {
        city_ = other.city_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getCountry().isEmpty()) {
        country_ = other.country_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      internalGetMutableProperties().mergeFrom(
          other.internalGetProperties());
      bitField0_ |= 0x00000004;
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              city_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              country_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              com.google.protobuf.MapEntry<String, String>
              properties__ = input.readMessage(
                  PropertiesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableProperties().getMutableMap().put(
                  properties__.getKey(), properties__.getValue());
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private Object city_ = "";
    /**
     * <code>string city = 1;</code>
     * @return The city.
     */
    public String getCity() {
      Object ref = city_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        city_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string city = 1;</code>
     * @return The bytes for city.
     */
    public com.google.protobuf.ByteString
        getCityBytes() {
      Object ref = city_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        city_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string city = 1;</code>
     * @param value The city to set.
     * @return This builder for chaining.
     */
    public Builder setCity(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      city_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string city = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCity() {
      city_ = getDefaultInstance().getCity();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string city = 1;</code>
     * @param value The bytes for city to set.
     * @return This builder for chaining.
     */
    public Builder setCityBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      city_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private Object country_ = "";
    /**
     * <code>string country = 2;</code>
     * @return The country.
     */
    public String getCountry() {
      Object ref = country_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        country_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string country = 2;</code>
     * @return The bytes for country.
     */
    public com.google.protobuf.ByteString
        getCountryBytes() {
      Object ref = country_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        country_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string country = 2;</code>
     * @param value The country to set.
     * @return This builder for chaining.
     */
    public Builder setCountry(
        String value) {
      if (value == null) { throw new NullPointerException(); }
      country_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string country = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearCountry() {
      country_ = getDefaultInstance().getCountry();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string country = 2;</code>
     * @param value The bytes for country to set.
     * @return This builder for chaining.
     */
    public Builder setCountryBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      country_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private com.google.protobuf.MapField<
        String, String> properties_;
    private com.google.protobuf.MapField<String, String>
        internalGetProperties() {
      if (properties_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            PropertiesDefaultEntryHolder.defaultEntry);
      }
      return properties_;
    }
    private com.google.protobuf.MapField<String, String>
        internalGetMutableProperties() {
      if (properties_ == null) {
        properties_ = com.google.protobuf.MapField.newMapField(
            PropertiesDefaultEntryHolder.defaultEntry);
      }
      if (!properties_.isMutable()) {
        properties_ = properties_.copy();
      }
      bitField0_ |= 0x00000004;
      onChanged();
      return properties_;
    }
    public int getPropertiesCount() {
      return internalGetProperties().getMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; properties = 3;</code>
     */
    @Override
    public boolean containsProperties(
        String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      return internalGetProperties().getMap().containsKey(key);
    }
    /**
     * Use {@link #getPropertiesMap()} instead.
     */
    @Override
    @Deprecated
    public java.util.Map<String, String> getProperties() {
      return getPropertiesMap();
    }
    /**
     * <code>map&lt;string, string&gt; properties = 3;</code>
     */
    @Override
    public java.util.Map<String, String> getPropertiesMap() {
      return internalGetProperties().getMap();
    }
    /**
     * <code>map&lt;string, string&gt; properties = 3;</code>
     */
    @Override
    public /* nullable */
String getPropertiesOrDefault(
        String key,
        /* nullable */
String defaultValue) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<String, String> map =
          internalGetProperties().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; properties = 3;</code>
     */
    @Override
    public String getPropertiesOrThrow(
        String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<String, String> map =
          internalGetProperties().getMap();
      if (!map.containsKey(key)) {
        throw new IllegalArgumentException();
      }
      return map.get(key);
    }
    public Builder clearProperties() {
      bitField0_ = (bitField0_ & ~0x00000004);
      internalGetMutableProperties().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; properties = 3;</code>
     */
    public Builder removeProperties(
        String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      internalGetMutableProperties().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @Deprecated
    public java.util.Map<String, String>
        getMutableProperties() {
      bitField0_ |= 0x00000004;
      return internalGetMutableProperties().getMutableMap();
    }
    /**
     * <code>map&lt;string, string&gt; properties = 3;</code>
     */
    public Builder putProperties(
        String key,
        String value) {
      if (key == null) { throw new NullPointerException("map key"); }
      if (value == null) { throw new NullPointerException("map value"); }
      internalGetMutableProperties().getMutableMap()
          .put(key, value);
      bitField0_ |= 0x00000004;
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; properties = 3;</code>
     */
    public Builder putAllProperties(
        java.util.Map<String, String> values) {
      internalGetMutableProperties().getMutableMap()
          .putAll(values);
      bitField0_ |= 0x00000004;
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:Address)
  }

  // @@protoc_insertion_point(class_scope:Address)
  private static final Address DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Address();
  }

  public static Address getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Address>
      PARSER = new com.google.protobuf.AbstractParser<Address>() {
    @Override
    public Address parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<Address> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<Address> getParserForType() {
    return PARSER;
  }

  @Override
  public Address getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
